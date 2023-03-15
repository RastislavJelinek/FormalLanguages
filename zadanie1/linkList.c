#include <stdlib.h>
#include <stdio.h>
#include "linkList.h"

node_t *add_node(node_t *head, int position) {
    node_t *new_node = malloc(sizeof(node_t));
    if (new_node == NULL) {
        fprintf(stderr, "Error: Out of memory\n");
        exit(EXIT_FAILURE);
    }
    new_node->position = position;
    new_node->next = NULL;
    if (head == NULL) {
        return new_node;
    }
    node_t *current = head;
    while (current->next != NULL) {
        current = current->next;
    }
    current->next = new_node;
    return head;
}

void printList(node_t *head) {
    printf("Matched positions: ");
    while (head != NULL) {
        printf("%d ", head->position);
        head = head->next;
    }
    printf("\n");
}

void free_list(node_t *head) {
    node_t *current = head;
    while (current != NULL) {
        node_t *next = current->next;
        free(current);
        current = next;
    }
}