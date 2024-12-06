# Restaurant Check Distribution
The purpose of this program is fairly distribute collected tips amongst the employees of a restaurant depending on their role.  This is based on check information input by the user.

## Process:
- Sale amount, tip amount, and total amount for each check are collected through user input.
  - If an input is blank, it is considered to be 0
  - Inputs that are negative, have more than two decimal places, exceed the integer limit, or are not numeric are discarded and the user is asked to try again
  - If the total amount is less than the sale amount, then the total amount is changed to equal the sale amount
  - If the sale amount and tip amount do not add up to the total amount, the tip amount is recalculated as the difference of the total amount and sale amount
- The total sale and tip amounts up to that check are calculated and the number of checks is incremented
- If the user chooses to continue, they will continue inputting checks
- Once the user chooses to stop, they are then promted to input the number of employees in each position
  - If the input is blank, it is considered to be 0
  - Inputs that are negative, exceed the integer limit, or are not numeric are discarded and the user is asked to try again
- Once the user is done, the tip distribution is calculated and printed using the total tip amount
  - 60% to the servers with 3 servers present
  - 20% to the kitchen
    - 8% to the chef
    - 6% to the sous-chef
    - 6% to the kitchen aid
  - 10% to the host/hostess
  - 10% to the busser
