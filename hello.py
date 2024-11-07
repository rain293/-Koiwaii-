from PIL import Image

try:
    img = Image.open('abc.jpg')
    print("Image opened successfully.")
    img.show()
    print("Show method called.")
except Exception as e:
    print(f"An error occurred: {e}")
    print(f"An error occurred: {e}")
    print(f"An error occurred: {e}")
    print(f"An error occurred: {e}")
