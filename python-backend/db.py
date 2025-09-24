import pymysql

def get_connection():
    return pymysql.connect(
        host='localhost',
        user='root',
        password='your_password',
        database='aws_services'
    )
