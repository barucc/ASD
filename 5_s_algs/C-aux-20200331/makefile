CC = gcc
CFLAGS = -Wall -g
SRCS = sort.c 
OBJS = $(SRCS:.c=.o)
HEADERS = $(SRCS:.c=.h)
MAIN = driver

all:    $(MAIN) 


$(MAIN): $(OBJS) $(MAIN).c
	$(CC) $(CFLAGS) -o $(MAIN) $(MAIN).c bucketsort.c quicksort_ip.c mergesort.c insertionsort_ip.c selectionsort_ip.c heap.c $(OBJS)

%.o: %.c %.h
	gcc -g -c $(CFLAGS) $*.c -o $*.o

clean:
	rm *.o $(MAIN)
