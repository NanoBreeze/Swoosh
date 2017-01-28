# Swoosh
Swoosh is an interpreted dynamic procedural language that includes recursion and nested control statements. Its syntax was inspired from Pascal and I built it to learn more about interpreter design.

Swoosh supports two types: strings and numbers. Procedures start with the `PROCEDURE` keyword and have no return values (I know, pretty shocking but bear in mind that this was a learning project). All assignments are printed to the console and all procedure definitions must appear before the entry point of the program, which is denoted with the `START` keyword.

```python
PROCEDURE factorial(a, b);
BEGIN
product = a*b;
if ( b > 1)
THEN
factorial(product, b-1);
END;

START
BEGIN
factorial(10, 9);
END
END
```
