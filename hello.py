import json

mydata = {
    'name': 'John',
    'age': 10
}
jsonstring = json.dumps(mydata)
print(jsonstring)