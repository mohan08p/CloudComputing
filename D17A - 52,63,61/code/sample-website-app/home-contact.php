<?php include('header.php'); ?>

		<div class="jumbotron">
			<div class="container">
				<div class="row">
					<div class="col-md-6">
						<div class="jumbotron-content">
							<h5>Introducing Lucid Theme</h5>
							
							<h1>Carefully crafted and beautiful landing page.</h1>
							
							<p>Etiam ullamcorper et turpis eget hendrerit. Praesent varius risus mi, at elementum magna ultricies acum magna ultricies accumsan. Cras venenatis lacus sed dolor placerat tempus. Morbi blandit at neque ut imperdiet.</p>
													
							<div class="jumbotron-actions">
								<a href="#" class="btn btn-primary">Download Now</a>
													
								<a href="#" class="btn btn-dark">View Features</a>
							</div><!-- /.jumbotron-actions -->
						</div><!-- /.jumbotron-content -->
					</div><!-- /.col-md-6 -->
					
					<div class="col-md-4 col-md-offset-2">
						<div class="jumbotron-form-contacts">
							<?php if ($contact_form->sent): ?>
								<p>Thanks for contacting us! We will get in touch with you shortly.</p>
							<?php else: ?>
								<form action="" method="post">
									<div class="form-head">
										<h3>Get in touch</h3>

										<p>Please complete the form</p>
									</div><!-- /.form-head -->

									<div class="form-body">
										<div class="form-group <?php echo $contact_form->is_valid('contact-name') ? '' : 'has-error'; ?>">
											<label for="contact-name" class="hidden">Name</label>
											
											<div class="form-controls">
												<input type="text" class="form-control" name="contact-name" id="contact-name" value="<?php echo $contact_form->get_value('contact-name'); ?>" placeholder="Name *">
											</div><!-- /.form-controls -->
										</div><!-- /.form-group -->
										
										<div class="form-group <?php echo $contact_form->is_valid('contact-email') ? '' : 'has-error'; ?>">
											<label for="contact-email" class="hidden">Email</label>
											
											<div class="form-controls">
												<input type="text" class="form-control" name="contact-email" id="contact-email" value="<?php echo $contact_form->get_value('contact-email'); ?>" placeholder="Email *">
											</div><!-- /.form-controls -->
										</div><!-- /.form-group -->
										
										<div class="form-group <?php echo $contact_form->is_valid('contact-subject') ? '' : 'has-error'; ?>">
											<label for="contact-subject" class="hidden">Subject</label>
											
											<div class="form-controls">
												<input type="text" class="form-control" name="contact-subject" id="contact-subject" value="<?php echo $contact_form->get_value('contact-subject'); ?>" placeholder="Subject *">
											</div><!-- /.form-controls -->
										</div><!-- /.form-group -->
										
										<div class="form-group <?php echo $contact_form->is_valid('contact-message') ? '' : 'has-error'; ?>">
											<label for="contact-message" class="hidden">Message</label>
											
											<div class="form-controls">
												<textarea class="form-control" name="contact-message" id="contact-message" placeholder="Message *"><?php echo $contact_form->get_value('contact-message'); ?></textarea>
											</div><!-- /.form-controls -->
										</div><!-- /.form-group -->
									</div><!-- /.form-body -->
									
									<div class="form-actions">
										<input type="submit" name="contact-form" value="Send Message" class="btn btn-primary">
									</div><!-- /.form-actions -->
								</form>
							<?php endif; ?>
						</div><!-- /.jumbotron-form-contacts -->
					</div><!-- /.col-md-3 col-md-offset-3 -->
				</div><!-- /.row -->
			</div><!-- /.container -->
		</div><!-- /.jumbotron -->
	</header><!-- /.header -->

<?php include('fragments/home-inner.php'); ?>
<?php include('footer.php'); ?>