from bs4 import BeautifulSoup
import requests

def parseRSS():
    url = requests.get('https://rss.nytimes.com/services/xml/rss/nyt/World.xml')

    soup = BeautifulSoup(url.content, 'xml')
    items = soup.find_all('item')

    for item in items:
        title = item.title.text
        author = item.creator.text
        date = item.pubDate.text
        description = item.description.text
        print(f"Title: {title}\n\n Author: {author}\n\n Date: {date}\n\n Description: {description}\n\n")

parseRSS()