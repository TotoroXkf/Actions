class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: list) -> int:
        return ladder_length(beginWord, endWord, wordList)


def ladder_length(begin_word: str, end_word: str, word_list: list) -> int:
    graph = {}
    word_list.insert(0, begin_word)
    for i in range(0, len(word_list)):
        graph[word_list[i]] = []
        for j in range(0, len(word_list)):
            if check(word_list[i], word_list[j]):
                graph[word_list[i]].append(word_list[j])
    result = dfs(graph, begin_word, end_word)
    if result != 0:
        result = result + 1
    return result


def check(s1: str, s2: str) -> bool:
    count = 0
    for i in range(0, len(s1)):
        if s1[i] != s2[i]:
            count += 1
    return count == 1


def dfs(graph: dict, key: str, end_word: str, check_dict:dict) -> int:
    result = 0
    for node in graph[key]:
        if node == end_word:
            return 1
        value = dfs(graph, node, end_word)
        if value != 0:
            value += 1
            if result == 0:
                result = value
            else:
                result = min(result, value)
    return result


s1 = "hot"
s2 = "dog"
word = ["dog", "dot"]
print(ladder_length(s1, s2, word))
