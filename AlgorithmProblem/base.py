from random import *
from string import *

# LinkedList↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓


class ListNode:
    def __init__(self, value):
        self.next = None
        self.val = value


def create_linked_list(array: list):
    head = None
    tail = None

    for value in array:
        if head is None:
            head = ListNode(value)
            tail = head
        else:
            new_node = ListNode(value)
            tail.next = new_node
            tail = tail.next
    return head


def print_linked_list(head: ListNode):
    if head is None:
        print(None)

    node = head
    while node is not None:
        print(node.val, end=' ')
        node = node.next
    print()


# LinkedList ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

# Tree ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓


class TreeNode:
    def __init__(self, value):
        self.left = None
        self.right = None
        self.val = value


def create_tree(array: list, position=0):
    if array[position] is None:
        return None
    root = TreeNode(array[position])
    left_position = (position + 1) * 2 - 1
    right_position = (position + 1) * 2
    if left_position < len(array):

        root.left = create_tree(array, position=left_position)
    if right_position < len(array):
        root.right = create_tree(array, position=right_position)
    return root


def print_tree(root: TreeNode, mode="layer"):
    traverse(root, mode)
    print()


def traverse(root: TreeNode, mode: str):
    if root is not None:
        if mode == "pre":
            print(root.val, end=" ")
            traverse(root.left, mode)
            traverse(root.right, mode)
        elif mode == "in":
            traverse(root.left, mode)
            print(root.val, end=" ")
            traverse(root.right, mode)
        elif mode == "post":
            traverse(root.left, mode)
            traverse(root.right, mode)
            print(root.val, end=" ")
        elif mode == "layer":
            queue = [root]
            while len(queue) > 0:
                node = queue.pop(0)
                print(node.val, end=" ")
                if node.left is not None:
                    queue.append(node.left)
                if node.right is not None:
                    queue.append(node.right)


# Tree ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

# Testcase ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓


def create_array_test_case(start=0,
                           end=10,
                           length: int = 10,
                           repeat=True,
                           sorted=False):
    result = []
    if repeat:
        for i in range(length):
            value = randint(start, end)
            result.append(value)
    else:
        num_set = set()
        while len(result) < length:
            value = randint(start, end)
            if value not in num_set:
                num_set.add(value)
                result.append(value)
    if sorted:
        result.sort()
    return result


def create_string_test_case(length=10, lower=True, upper=False, repeat=True):
    if repeat:
        all_char = "abcdefghijkmlnopqrstuvwxyz"
        result = "".join(choices(population=all_char, k=length))
        if upper:
            return result.upper()
        return result
    else:
        if lower:
            return "".join(sample(ascii_lowercase, length))
        if upper:
            return "".join(sample(ascii_uppercase, length))
        return "".join(sample(ascii_letters, length))


def create_matrix_test_case(rows=5, cols=5, start=0, end=10, repeat=True):
    matrix = []
    for i in range(rows):
        matrix.append(
            create_array_test_case(start=start, end=end, length=cols, repeat=repeat))
    return matrix


# Testcase ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑
