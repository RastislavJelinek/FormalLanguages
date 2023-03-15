CC = gcc
CFLAGS = -g -Wall -Wextra -std=c99
OBJDIR = build
EXEDIR = exe

SOURCES = main.c zadanie1.c linkList.c
HEADERS = zadanie1.h linkList.h

SOURCES_TEST = zadanie1test.c linkList.c
HEADERS_TEST = zadanie1.h linkList.h

EXECUTABLE = zadanie1
EXECUTABLE_TEST = zadanie1_TEST

.PHONY: all clean

all:$(EXEDIR)/$(EXECUTABLE) $(EXEDIR)/$(EXECUTABLE_TEST)

$(EXEDIR)/$(EXECUTABLE): $(OBJDIR)/main.o $(OBJDIR)/zadanie1.o $(OBJDIR)/linkList.o 
	mkdir -p $(EXEDIR)
	$(CC) $(CFLAGS) $^ -o $@

$(EXEDIR)/$(EXECUTABLE_TEST): $(OBJDIR)/zadanie1test.o $(OBJDIR)/zadanie1.o $(OBJDIR)/linkList.o
	mkdir -p $(EXEDIR)
	$(CC) $(CFLAGS) $^ -o $@

$(OBJDIR)/linkList.o: linkList.c linkList.h
	mkdir -p $(OBJDIR)
	$(CC) $(CFLAGS) -c $< -o $@

$(OBJDIR)/main.o: main.c zadanie1.h
	mkdir -p $(OBJDIR)
	$(CC) $(CFLAGS) -c $< -o $@

$(OBJDIR)/zadanie1.o: zadanie1.c zadanie1.h linkList.h
	mkdir -p $(OBJDIR)
	$(CC) $(CFLAGS) -c $< -o $@

$(OBJDIR)/zadanie1test.o: zadanie1test.c zadanie1.h 
	mkdir -p $(OBJDIR)
	$(CC) $(CFLAGS) -c $< -o $@

clean:
	rm -rf $(OBJDIR) $(EXEDIR)
