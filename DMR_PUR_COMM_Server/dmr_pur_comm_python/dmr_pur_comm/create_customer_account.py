import requests
import json
import socket
import sys

apikey = '86f4fff6bb51a844674758c25938e27f'

url = "http://api.nessieisreal.com"
#while True:
resp = ""

fn = sys.argv[1]
ln = sys.argv[2]
st_num = sys.argv[3].split(",")[0]
st_name = sys.argv[3].split(",")[1]
city = sys.argv[4]
state = sys.argv[5]
z_code = sys.argv[6]
amt = int(sys.argv[7])
url_cust = url+"/customers?key="+apikey
body = {
        "first_name":fn,
        "last_name":ln,
        "address":{
            "street_number":st_num,
            "street_name":st_name,
            "city":city,
            "state":state,
            "zip":z_code
            }
        }
cr_cust = requests.post(url_cust,data = json.dumps(body),
                       headers={'content-type':'application/json'})
#print(cr_cust.json())
if(cr_cust.json()['code']==201):
    cust_id = cr_cust.json()['objectCreated']['_id']
    url_acc = url+"/customers/"+cust_id+"/accounts?key="+apikey
    acc_body = {
        "type":"Checking",
        "nickname":fn[0]+ln[0],
        "rewards":0,
        "balance":amt,
        }
    cr_acc = requests.post(url_acc,data = json.dumps(acc_body),
                          headers={'content-type':'application/json'})
#    print(cr_acc.json())
    if(cr_acc.json()['code']==201):
        resp = "Success/"+cust_id+"/"+cr_acc.json()['objectCreated']['_id']
    else:
        resp = "Failure"
else:
    resp = "Failure"
print(resp)
sys.exit(resp)
