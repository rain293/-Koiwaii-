from bs4 import BeautifulSoup

note = '''
<?xml version="1.0" encoding="UTF-8"?>
<breakfast_menu>
    <food>
        <name>Belgian Waffles</name>
        <price>$5.95</price>
        <description>Two of our famous Belgian Waffles with plenty of real maple syrup</description>
        <calories>650</calories>
    </food>
    <food>
        <name>Strawberry Belgian Waffles</name>
        <price>$7.95</price>
        <description>Light Belgian waffles covered with strawberries and whipped cream</description>
        <calories>900</calories>
    </food>
</breakfast_menu>
'''
soup = BeautifulSoup(note,'xml')

foods = soup.find_all('food')

for food in foods:
    name  = food.find('name').text
    price  = food.find('price').text
    print(f'{name}:{price}')

