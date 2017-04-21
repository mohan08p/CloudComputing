from flask import Flask, session
from flask import render_template
from flask import request
import models as dbHandler
import requests
import json
import os
from wtforms import Form, TextField, TextAreaField, validators, StringField, SubmitField

# App config.
DEBUG = True
app = Flask(__name__)
app.config.from_object(__name__)
app.config['SECRET_KEY'] = '7d441f27d441f27567d441f2b6176a'

@app.route('/')
def home():
    return render_template('home.html')

@app.route('/signup.html', methods=['POST', 'GET'])
def signup():
    if request.method=='POST':
        username = request.form['username']
        password = request.form['password']
        session['user'] = username
        dbHandler.insertUser(username, password)
        users = session['user']
        return render_template('thanks.html', users=username)
    else:
        return render_template('signup.html')

@app.route('/signin.html', methods=['POST', 'GET'])
def signin():
    if request.method=='POST':
        username = request.form['username']
        password = request.form['password']
        dbHandler.checkUsers(username, password)
        check = dbHandler.checkUsers(username,password)
        if check=="true":
            return render_template('goods.html', users=username)
        else:
            return render_template('signin.html')
    else:
        return render_template('signin.html')

@app.route('/todo', methods=['POST', 'GET'])
def todo():
    if request.method=='POST':
        text = request.form['text']
        resp = requests.post('http://192.168.100.61/', json = {'text':text})
        message = resp.json()['status']
    return render_template('goods.html', users=session['user'], message=message)


if __name__ == "__main__":
    app.run()
