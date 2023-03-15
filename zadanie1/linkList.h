#ifndef LINKLIST_H
#define LINKLIST_H
typedef struct node {
    int position;
    struct node *next;
} node_t;
node_t *add_node(node_t *head, int position);
void printList(node_t *head);
void free_list(node_t *head);
#endif // LINKLIST_H