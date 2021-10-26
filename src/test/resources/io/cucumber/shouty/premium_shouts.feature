Feature: Premium account

  Rules:
  * Mention the word "buy" and you lose 5 credits.
  * Long messages cost 2 credits.

  Background:
    Given the range is 100
    And people are located at
      | name     | Sean | Lucy |
      | location | 0    | 100  |

  Scenario: Test premium account feature
    Given Sean has bought 30 credits
    When Sean shouts "Come and buy a coffee"
    And Sean shouts "My bagels are yummy"
    And Sean shouts "Free cookie with your espresso for the next hour"
    And Sean shouts the following message
    """
    You need to come and visit Sean's coffee,
    we have the best bagels in town.
    """
    And Sean shouts "Who will buy my sweet red muffins?"
    And Sean shouts the following message
    """
    This morning I got up early
    and baked some bagels especially for you.
    Then I fried some sausage.
    I went out to see my chickens,
    they had some delicious fresh eggs waiting for me
    and I scrambled them just for you.
    Com on over and let's eat breakfast!
    """
    And Sean shouts "We have cakes by the dozen"
    Then Lucy hears all Sean's messages
    And Sean should have 11 credits

  @todo
  Scenario: BUG #2789
    Given Sean has bought 30 credits
    When Sean shouts "buy, buy buy!"
    Then Sean should have 25 credits