import requests
import json
import socket
import sys

apikey = '86f4fff6bb51a844674758c25938e27f'
url = "http://api.nessieisreal.com"

response = ""

acc_id = sys.argv[1]
sell_id = sys.argv[2]
amt = sys.argv[3]


url_tran = url+"/accounts/"+acc_id+"/transfers?key="+apikey
body = {
    "medium": "balance",
    "payee_id": sell_id,
    "amount": float(amt)
    }
resp = requests.post(url_tran, data = json.dumps(body),
                     headers={'content-type':'application/json'})
if(resp.json()['code']==201):
    print("Success")
    response = "Success"
else:
    print("Failure")
    response = "Failure"

#print(amt)
sys.exit(response)
'''
url_tran = url+"/accounts/"+acc_id+"/transfers?key="+apikey
resp = requests.get(url_tran, headers={'content-type':'application/json'})
print(resp.json())
'''