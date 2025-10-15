Feature: Product Catalog

  Scenario: The one where Sally searches for and Adjustable Wrench
    Given Sally is on the homepage
    When she searches for "Adjustable Wrench"
    Then the "Adjustable Wrench" product should be displayed

  Scenario: The one where Sally searches for products by name
    Given Sally is on the homepage
    When she searches for "saw"
    Then the following product should be displayed
      | Wood Saw     |
      | Circular Saw |

#  Scenario: The one where Sally searches for a more general term
#    Given Sally is on the homepage
#    When she searches for "saw"
#    Then the following product table should be displayed
#      | Product      | Price  |
#      | Wood Saw     | $12.18 |
#      | Circular Saw | $80.19 |
#
#  Scenario: The one where Sally searches for product that doesn't exist
#    Given Sally is on the homepage
#    When she searches for "product-does-not-exist"
#    Then no product should be displayed
#    And "There are no products found." should be displayed
#
#  Scenario: The one where Sally wants to see hand Saws
#    Given Sally is on the homepage
#    When she searches for "saw"
#    And she filters by "Hand Saw"
#    Then the following product table should be displayed
#      | Product      | Price  |
#      | Wood Saw     | $12.18 |
#
#  Scenario Outline: Sally sorts by different criteria
#    Given Sally is on the homepage
#    When she sorts by "<Sort>"
#    Then the first product displayed should be "<First_Product>"
#    Examples:
#      | Sort               | First_Product       |
#      | Name (A - Z)       | Adjustable Wrench   |
#      | Name (Z - A)       | Wood Saw            |
#      | Price (High - Low) | Drawer Tool Cabinet |
#      | Price (Low - High) | Washers             |