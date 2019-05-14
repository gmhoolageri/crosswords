# crosswords

This project can solve your crossword puzzle. Clone the project, run it on server using simple java -jar command. Once the application is running fire a rest(or curl) request to get the words.

Example 1:

http://host:1983/w/w/9/

http://host:1983/w/w/ is the base path and 9 is the length of the string requested to the server.

Exaple 2:
http://host:1983/w/w/9/1:C,9:R

You can also extend this by giving the additional info about the string like character location.

1:C,9:R represents that 9 character long word has C as a first character and R as a 9th character.

Happy Solving

@gmhoolageri

