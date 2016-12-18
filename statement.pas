BEGIN
    BEGIN {Temperature conversions.}
        a = 3;
        b = 3;



    WHILE (a < 4) OR (b == 1) DO
    BEGIN
    IF a < 4
    THEN
    b = 88;
    a = a+1;
    END

          IF a < 45
            THEN
            b = 34;
            ELSE
            BEGIN
            a = 88;
            b = 43;
            END
        five  = -1 + 2 - 3 + 4 + 3;
        ratio = five/9.0;

        fahrenheit = 72;
        centigrade = (fahrenheit - 32)*ratio;

        centigrade = 25;
        fahrenheit = centigrade/ratio + 32;
        centigrade = 25;
        fahrenheit = 32 + centigrade/ratio;
    END;

    {Runtime division by zero error.}
    dze = fahrenheit/(ratio - ratio);

    BEGIN {Calculate a square root using Newton's method.}
        number = 2;
        root = number;
        root = (number/root + root)/2;
    END;


END.
