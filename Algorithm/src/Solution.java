class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return mergeKLists(lists, 0, lists.length - 1);
    }

    public ListNode mergeKLists(ListNode[] lists, int start, int end) {
        if (start >= lists.length) {
            return null;
        }
        if (start >= end) {
            return lists[start];
        }
        int mid = (start + end) / 2;
        ListNode list1 = mergeKLists(lists, start, mid);
        ListNode list2 = mergeKLists(lists, mid + 1, end);
        return mergeTwoLists(list1, list2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newLinkedPreHead = new ListNode(0);
        ListNode newNode = newLinkedPreHead;
        ListNode node1 = l1;
        ListNode node2 = l2;
        while (node1 != null || node2 != null) {
            if (node1 == null) {
                newNode.next = node2;
                break;
            } else if (node2 == null) {
                newNode.next = node1;
                break;
            } else {
                if (node1.val < node2.val) {
                    newNode.next = node1;
                    node1 = node1.next;
                } else {
                    newNode.next = node2;
                    node2 = node2.next;
                }
                newNode = newNode.next;
            }
        }
        return newLinkedPreHead.next;
    }
}