My initial solution to the Explore phase was fairly naive, just looking at neighbouring cells and choosing the closest. I added history to cope with dead ends, which worked quite well because I had to stop keeping history while retracing steps, so dead ends would be permanently cut off from the
search.

When I got to the Escape phase I realised how good it would be to be able to have a method which would calculate the optimal path between two nodes. I
spent an evening learning how Djikstra's algorithm works, and another one implementing it.

For the escape phase I set the nodes to point to the exit in one pass of Djikstra; I then explore for gold until there is only enough time to head for
the exit. Good in theory, but I could not figure out how to get the exact time remaining. I knew it was generated by

 minTimeToEscape + EXTRA_TIME_FACTOR * (Cavern.MAX_EDGE_WEIGHT + 1) * escapeCavern.numOpenTiles() / 2

but I did not have time to reverse-engineer minTimeToEscape and add the edges in to calculate it properly.

So, if I was to improve my solution I would calculate the exact actual 'time remaining' for each node on the escape phase. That would be far more
optimal and would yield higher scores. As it is, since the ratio between timeRemaining and pathLength is an approximation, I was forced to be quite
conservative with when to head for the exit, in order to survive all edge cases. In my current solution there is often quite a lot of time left on the
clock.

I could also look to try different paths and choose the best one based on the amount of gold collected. I did have a go at that but just didn't have
time to implement it safely.

I could also add more testing to the Explorer class; as well as have copied the implementation of Djikstra from the given code, which is probably
better than mine. I could also have searched for the pizza, which I spotted a couple of times.

The style and DRYness of my code could also have been improved a lot with another day or two of work - as it is it's not particularly beautiful or easy to read! Many methods are far longer than they should be.

I genuinely enjoyed this coursework, and learned a lot, particularly about Djikstra's algorithm and pathfinding in general. I completed this coursework
without a partner.
