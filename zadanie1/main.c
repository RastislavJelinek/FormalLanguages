#include <stdio.h>
#include "zadanie1.h"
#include "linkList.h"

int main(void) {
    start(NULL);
    node_t* a = get_matches();
    printf("\n");
    printf("Amount of matches: %d\n",getAmount());
    printList(a);
    free_list(a);
    return 0;
}