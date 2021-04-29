import random

total = 100
now_total = 100
index = 5
i = 5
result = []
result_sign = []


def get_bid(chipsLeft):
    try:
        count = int(input("出价多少芯片(1-%d)" % chipsLeft))
        if count <= chipsLeft and count > 0:
            return count
    except:
        pass
    print("无效的芯片数量")
    return get_bid(chipsLeft)
        

def display_details():
    global i
    print("比赛次数:%d" % (index - i))
    win = 0
    lose = 0
    size = len(result)
    for i in range(0, size):
        ele = result[i]
        sign = result_sign[i]
        notice = ""
        if not sign:
            notice = "你输了"
            lose += 1
        else: 
            notice = "你赢了"
            win += 1
        print("第%d轮:你出价%d个筹码," % (i + 1, ele) + notice)
    print(("赢几轮:%d,输几轮:%d,胜率:%.2f" % (win, lose, win/size * 100)) + "%," + notice)
    print("剩余芯片:%d" % now_total)
    print("谢谢你的参与")
    
def get_choice():
    choice = input("左还是右[l, r]")
    if choice == "l" or choice == "r":
        return choice
    print("无效的选择!")
    return get_choice()
    
def get_yn():
    choice = input("继续玩[y, n]")
    if choice == "y" or choice == "n":
        return choice
    print("无效的选择!")
    return get_yn()
    
def play_dodge():
    global i
    global now_total
    choice_arr = ["l", "r"]
    while i > 0:
        count = get_bid(now_total)
        choice = get_choice()
        print("你选择了:" + choice)
        computer = choice_arr[random.randint(0, 1)]
        print("计算机选择了:" + computer)
        result.append(count)
        if computer != choice:
            print("你输了")
            now_total -= count
            result_sign.append(False)
        else: 
            print("你赢了")
            now_total += count
            result_sign.append(True)
        print("你现在有%d个筹码" % now_total)
        i = i -1
        if i == 0:
            display_details()
            break
        yn = get_yn()
        if yn == "n":
            display_details()
            break
        
if __name__ == '__main__':
    play_dodge()
