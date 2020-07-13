# Etrust-Shop
# Folder Structure
src>test>java>EtrustTest.java
# Set up and pre-requisite to run the test case
1. Java 8 
2. ChromeDriver.exe path set in the environment variable.
3. Buil a maven project

# More information
Test will run with TestNg runner.

# Test cases and description
1. verifyTitle (Check that the page title equals “zalando.de Bewertungen & Erfahrungen | Trusted Shops”)
This test case verify the title and will fail if condition of expected and actual did not match.

2. gradeCheck (Check that the grade is shown and is above zero (e.g. 4.81/5.00))
Since,page is not mentioned and as per sequence of requirement I cannot see any grade on the landing page, so I assume it to be any page , so this test case will click "bekleidung" at the bottom and  print all the grades of one page and then compare whether grades greater than 0 or not.
Note #1: In requirement it is mentioned grade greater than 0, however I could not find 0 grade in a page ,so for this test case I assume no grade equals to 0 and I compare no grade value .

3. withAndWithoutGrades(Click on the bottom of the list to load more reviews and validate that it happened)
This test case print list of all the websites with and without grades for all the pages(1 till the end). Requirement was not clear about pages so, code will check each and every page. Also, this test can be use with any categories appearing at the bottom of the page by changing the name of the category in line #49.
Note #1: Assumptions same as test case #2.

# Requirement Understanding
Click on “Mangelhaft” and validate that it loaded some reviews- this requirement is unclear - need more information.

# Additional Information
Since, some of the requirements are not clear and I assumed few points.On high-level I understand what is required from these requirements so this code will try to fullfill the objective of the regression.