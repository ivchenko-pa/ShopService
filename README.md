# Shop Service

## Service for placing orders for different products retrieved from the EAN database:
```text
4006381333931,Organic Apple Juice,3.49
4006381333948,Premium Coffee Beans,12.99
...
4006381334112,Red Lentils 500g,2.39
4006381334129,Italian Herb Mix,2.89
```
## The user can provide zero or more product IDs. If no ID is provided, the order will be cancelled.
```text
Would you like to place an order? [Y/N] 
y
Available products:
Product[id=4006381334037, name=Peanut Butter Creamy, price=4.49]
Product[id=4006381334044, name=Orange Juice 1L, price=2.99] 
...
Product[id=4006381334006, name=Strawberry Jam, price=3.59]    <<<<<<<<<<<
Product[id=4006381333979, name=Extra Virgin Olive Oil, price=8.99]
Please provide product id(s) you would like to order (separated by " ")
4006381334006      <<<<<<<<<<<
Order placed: ID_1      <<<<<<<<<<<
```
## Receipts for all placed orders can be printed in a human-readable format.
```text
***********************************
**********  ORDER: ID_1  **********   <<<<<<<<<<<
***********************************
* Strawberry Jam            3.59  *   <<<<<<<<<<<
*                           x1    *
*                                 *
*        Total price:   3.59 EURO *
***********************************
***********************************
-✄--✄--✄--✄--✄--✄--✄--✄--✄--✄--✄--✄-
***********************************
**********  ORDER: ID_20 **********
***********************************
* Whole Wheat Pasta         1.89  *
*                           x2    *
* Granola 400g              4.29  *
*                           x2    *
* Almond Milk 1L            2.79  *
*                           x1    *
* Peanut Butter Creamy      4.49  *
*                           x2    *
* Honey 500g                5.49  *
*                           x4    *
* Blueberry Yogurt          1.49  *
*                           x2    *
* Orange Juice 1L           2.99  *
*                           x3    *
*                                 *
*        Total price:  58.04 EURO *
***********************************
***********************************
-✄--✄--✄--✄--✄--✄--✄--✄--✄--✄--✄--✄-
***********************************
**********  ORDER: ID_12 **********
***********************************
* Organic Apple Juice       3.49  *
*                           x2    *
* Almond Milk 1L            2.79  *
*                           x1    *
* Peanut Butter Creamy      4.49  *
*                           x1    *
* Blueberry Yogurt          1.49  *
*                           x1    *
*                                 *
*        Total price:  15.75 EURO *
***********************************
***********************************
-✄--✄--✄--✄--✄--✄--✄--✄--✄--✄--✄--✄-
```
