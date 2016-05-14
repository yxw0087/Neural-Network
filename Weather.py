from urllib.request import urlopen

from bs4 import BeautifulSoup

 
# Iterate through year, month, and day
for y in range(1945, 2015):
  for m in range(7, 9):
    for d in range(1, 32):
 
      # Create/open a file called wunder.txt (which will be a comma-delimited file)
      f1 = open('max-temp.txt', 'a')
      f2 = open('min-temp.txt', 'a')
      f3 = open('rain-fall.txt', 'a')
      f4 = open('wind-speed.txt', 'a')
      f5 = open('humidity.txt', 'a')
 
      # Open wunderground.com url
      url = "http://www.wunderground.com/history/airport/KLFT/"+str(y)+ "/" + str(m) + "/" + str(d) + "/DailyHistory.html"
      page = urlopen(url)
 
      # Get temperature from page
      soup = BeautifulSoup(page)
      
      maxTemp = soup.find(text='Max Temperature').find_next('span', {'class': 'wx-value'}).text      
      f1.write(maxTemp + '\n')

      minTemp = soup.find(text='Min Temperature').find_next('span', {'class': 'wx-value'}).text
      f2.write(minTemp + '\n')      

      rain = soup.find(text='Precipitation').find_next('span', {'class': 'wx-value'}).text
      f3.write(rain + '\n')

      #wind = soup.find(text='Wind Speed').find_next('td').text
      f4.write(wind + '\n')

      humidity = soup.find(text='Average Humidity').find_next('td').text
      f5.write(humidity + '\n')     
 
      # Done getting data! Close file.
      f1.close()
      f2.close()
      f3.close()
      f4.close()
      f5.close()
