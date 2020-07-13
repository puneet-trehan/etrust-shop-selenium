# Etrust-Shop
# Folder Structure
src>test>java>EtrustTest.java
# Set up and pre-requisite to run the test case
1. Java 8 
2. ChromeDriver.exe path set in the environment variable.
3. Buil a maven project

# Running test case
Test will run with TestNG runner to run the test case right click on the method with '@Test' and select run as TestNG

# Test cases and description
1. verifyTitle (Check that the page title equals “zalando.de Bewertungen & Erfahrungen | Trusted Shops”)
This test case verify the title and will fail if condition of expected and actual did not match. I assumed it to fail the test case if the title does not match.

2. gradeCheck (Check that the grade is shown and is above zero (e.g. 4.81/5.00))
Since,page is not mentioned and as per sequence of requirement I cannot see website with grade on landing page, so I assume it to take page which has grades and websites, so this test case click category "Koffer, Taschen & Lederwaren" at the bottom and print all the grades and comparison result if greater than or equal to 0.
Note #1: In requirement it is mentioned grade greater than 0, however I could not find 0 grade in a page ,so for this test case I assume no grade equals to 0 and I compare no grade value .

3. withAndWithoutGrades(Click on the bottom of the list to load more reviews and validate that it happened)
Requirement was not clear about pages whether to print in list or in sequence and also whether to pass or fail the test case when find 0 grade. At the end the list of total websites with and without grades for all the pages(1 till the end). Also, this test can be use with any categories appearing at the bottom of the page by changing the name of the category in line #48. This test case will use three methods, method #1 printList : Print list of all the websites. #2 getGradeInfo: Grade information with grade equal to 0 or grade greater than 0. #3. waitForPageToLoad: Is use ,to wait until all java script elements are visible.
Note #1: Assumptions same as test case #2.

# Requirement Understanding
Click on “Mangelhaft” and validate that it loaded some reviews- this requirement is unclear - need more information.

# Additional Information
Since, some of the requirements are not clear and I assumed few points.On high-level I understand what is required from these requirements so this code will try to fullfill the objective of the regression.
