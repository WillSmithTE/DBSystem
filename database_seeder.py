import pandas as pd  
import numpy as np 
import time, re, json 
import mysql.connector
from faker import Faker

# DB Connection
connection = mysql.connector.connect(host="charity-db.cp7zsxa1pi0j.ap-southeast-2.rds.amazonaws.com ",user="master", passwd="fRSnoXZ7139N", database="dev" )
c = connection.cursor()

#Seed Donor table

def seedDonor():
	fake = Faker()
	for i in range(500):
		try:
			
			insert_sql = (
				"""INSERT INTO `donor` (contact_number, email, email_confirmed, name, password, created_at) 
				VALUES (%s,%s,%s,%s,%s,"CURRENT_TIMESTAMP()");"""
			)
		   
			data = (str(fake.phone_number()), (fake.last_name_male() + "@test.com.au"), 1,fake.name(), "A*^&$GD!&IHD")
			
			c.execute(insert_sql, data)
		
		except Exception as e:
			print(e)
		connection.commit()

seedDonor()