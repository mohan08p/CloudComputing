<?php

$contact_form = new Contact_Form(
	'youremail@example.com'
);

class Contact_Form {
	public $receiver = '';

	public $errors = array();
	public $fields = array();
	public $sent = false;

	function __construct($receiver) {
		$this->receiver = $receiver;

		$this->fields = array(
			'contact-name' => array(
				'value' => '',
				'label' => 'Name',
				'type' => 'text',
				'required' => true,
			),
			'contact-email' => array(
				'value' => '',
				'label' => 'Email',
				'type' => 'email',
				'required' => true,
			),
			'contact-subject' => array(
				'value' => '',
				'label' => 'Subject',
				'type' => 'text',
				'required' => true,
			),
			'contact-message' => array(
				'value' => '',
				'label' => 'Message',
				'type' => 'textarea',
				'required' => true,
			),
		);

		if (!isset($_POST['contact-form'])) {
			return;
		}

		foreach ($this->fields as $name => &$data) {
			if (!empty($_POST[$name])) {
				$data['value'] = htmlspecialchars($_POST[$name]);
			}

			if ($data['required']) {
				$error = false;

				switch ($data['type']) {
					case 'email':
						$error = !filter_var($data['value'], FILTER_VALIDATE_EMAIL);
					break;
					
					default:
						$error = $data['value'] === '';
					break;
				}

				if ($error) {
					$this->errors[] = $name;
				}
			}
		}

		if (!$this->errors) {
			$from = $this->fields['contact-email']['value'];
			$subject = 'Lucid - Contact Form';

			//email body
			$message = '';
			foreach ($this->fields as $field) {
				$message .= '<strong>' . $field['label'] . ':</strong> ' . ($field['type'] == 'textarea' ? '<br /><br />' : '') . nl2br($field['value']) . '<br /><br />';
			}

			//email headers
			$headers = 'From: ' . $from .'' . "\r\n";
			$headers .= 'Reply-To: ' . $from . '' . "\r\n";
			$headers .= 'MIME-Version: 1.0' . "\r\n" . "Content-type: text/html; charset='utf-8'" . "\r\n";
			$headers .= 'X-Mailer: PHP/' . phpversion();

			$this->sent = mail($this->receiver, $subject, $message, $headers);
		}
	}

	function is_valid($name) {
		if (in_array($name, $this->errors)) {
			return false;
		}

		return true;
	}

	function get_value($name) {
		$value = '';

		if (isset($this->fields[$name])) {
			$value = $this->fields[$name]['value'];
		}

		return $value;
	}
}

