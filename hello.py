from PIL import Image
import webbrowser
import os

# Check existing files
print("Files in directory:", os.listdir('.'))

# Open the correct image file
img = Image.open('abc.jpg')  # Change to the correct filename

# Convert to RGB if necessary
if img.mode == 'RGBA':
    img = img.convert('RGB')

# Save the image
img.save('temp_image.jpg')

# Create and open the HTML file to display the image
html_content = """
<html>
<body>
    <h1>Image Display</h1>
    <img src="temp_image.jpg" alt="Image">
</body>
</html>
"""
with open("display_image.html", "w") as f:
    f.write(html_content)

webbrowser.open("display_image.html")
