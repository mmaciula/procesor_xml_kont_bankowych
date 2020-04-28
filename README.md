# Procesor xml kont bankowych
An application processing a list of bank accounts based on xml files. On input, it accepts the `*.xml` file with the list of bank accounts on which the following operations should be performed:
* rejecting accounts with a currency other than that indicated
* rejection of accounts with a balance below zero
* rejected accounts with a closing date earlier than the current date
* rejection of accounts with an IBAN number incorrect for the selected bank account type
* sorting in ascending order by account name


### Example *input.xml* file
```
<?xml version = "1.0"?>
<accounts>
	<account iban="PL61109010140000071219812870">
		<name>name4</name>
		<currency>PLN</currency>
		<balance>0</balance>
		<closingDate>2029-10-11</closingDate>
	</account>

	<account iban="PL61109010140000071219812875">
		<name>name1</name>
		<currency>PLN</currency>
		<balance>123.45</balance>
		<closingDate>2031-06-10</closingDate>
	</account>

	<account iban="PL61109010140000071219812871">
		<name>name2</name>
		<currency>PLN</currency>
		<balance>85.00</balance>
		<closingDate>2035-10-01</closingDate>
	</account>

	<account iban="PL61109010140000071219812872">
		<name>name3</name>
		<currency>USD</currency>
		<balance>1000000.00</balance>
		<closingDate>2059-10-01</closingDate>
	</account>

	<account iban="PLL1109010140000071219812873">
		<name>name5</name>
		<currency>PLN</currency>
		<balance>999.00</balance>
		<closingDate>2050-01-01</closingDate>
	</account>

	<account iban="PL61109010140000071219812874">
		<name>name6</name>
		<currency>PLN</currency>
		<balance>-100</balance>
		<closingDate>2039-05-15</closingDate>
	</account>

	<account iban="PLL1109010140000071219812876">
		<name>name7</name>
		<currency>PLN</currency>
		<balance>1.00</balance>
		<closingDate>2010-01-01</closingDate>
	</account>
</accounts>
```

## Run
The application can be run using Maven:
```
mvn build
```
followed by:
```
mvn exec:java
```
