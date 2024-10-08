# Ví dụ chuỗi
paragraph	= """This-is	line	1
This	is-	line	2-
This	-is	line	3"""

abc = paragraph.strip('3')

print(abc)
#I want to create a chat socket app by using python
import socket
# create a socket object
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
# get local machine name
host = socket.gethostname()
# define the port on which you want to connect
port = 12345
# connect to the server on local computer
s.connect((host, port))
# receive data from the server
msg = s.recv(1024)
# close the connection
s.close()

