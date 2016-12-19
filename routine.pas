PROCEDURE fibonacci(a, b);
BEGIN
sum = a + b;
IF sum < 1000
THEN
fibonacci(b, sum);
END;

PROCEDURE factorial(a, b);
BEGIN
product = a*b;
if ( b > 1)
THEN
factorial(product, b-1);
END;

PROCEDURE hello();
BEGIN
a = 4;
END;

START
BEGIN
a = 45;
a = "oijoij";
b = "hey";
b = 12;
 { fibonacci(1, 1); }
 { factorial(5, 4); }
END