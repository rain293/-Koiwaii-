from bs4 import BeautifulSoup

html = '''
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Web Page</title>
</head>
<body>
    <h1>Welcome to My Web Page</h1>
    <p>This is a simple HTML document.</p>
    <a href="https://example.com">Visit Example.com</a>
    <div class="content">
        <h2>About</h2>
        <p>This section is about something interesting.</p>
    </div>
</body>
</html>
'''
    
abc = BeautifulSoup(html,'html.parser')

# tcp socket

#I want to create a script file that everytime my system code changed, It will automaticallly generate compile file (.class file) otherwise run app

