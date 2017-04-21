import sqlite3

def insertUser(username,password):
    database='website1.db'
    con = sqlite3.connect(database)
    cur = con.cursor()
    cur.execute("INSERT INTO users (username,password) VALUES (?,?)", (username,password))
    con.commit()
    con.close()

def checkUsers(username,password):
    database='website1.db'
    con = sqlite3.connect(database)
    cur = con.cursor()
    query_string = "SELECT password FROM users WHERE username = '{username}'".format(username=username)
    cur.execute(query_string)
    pw = cur.fetchall()
    pw1=pw[0]
    print(pw1)
    con.close()
    if password in pw1:
        print("true")
        return "true"
    else:
        print("false")
        return "false"

def init_db():
    database='website1.db'
    con = sqlite3.connect(database)
    f = open('schema.sql', 'r')
    con.cursor().executescript(f.read())
    con.commit()
    con.close()
