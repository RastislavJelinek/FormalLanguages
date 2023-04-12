#ifndef ZADANIE1_H
#define ZADANIE1_H
#include <stdbool.h>
#include "linkList.h"

void start(char* input);

node_t *get_matches();

int getAmount();
//read character from stdin, check if not EOf or '\n' 
char read_char();

bool checkTemporary();
void prinLister();
bool q0(char input);
//q1 and q7 were end states returning only 1, later delleted in refactoring
bool q1();
//q1 when: 'a' or 'ca'

//q2 state where you get when: 'b'
bool q2();

//q3 state where you get when: 'c'
bool q3();

//q4 state where you get when: 'bb'
bool q4();

//q5 state where you get when: 'bc' or 'bbb'
bool q5();

//q6 state where you get when: 'bba' or 'bca' or 'bbba'
bool q6();


bool q7();
bool q8();

//q7 state where you get when: 'bbab' or 'bcab' or 'bbbab'
#endif // ZADANIE1_H