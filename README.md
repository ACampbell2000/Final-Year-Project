# Final-Year-Project

For my Final Year Project I chose to create a new hybrid algorithm to solve the travelling salesman problem. 
To do this I went towards the approximation algorithms as a way to counteract the extremely high time complexity that actually solving this problem would create.
My program works by combining two existing methods of approximately solving the travelling salesman problem, the first method was a genetic algorithm, and the second method is called Christofides Algorithm.

Christofides algorithm works in such a way that its output is a solution to the travelling salesman problem that is proven to be at most 50% worse than the actual optimal solution. The way my program works is that it takes that output and then inserts it into the genetic algorithm as one of the population, from there it runs through the genetic algorithm until it reaches a predefined number of generations and then outputs the best solution that was found.
