import random

total = 100
now_total = 100
index = 5
i = 5
result = []
result_sign = []


def get_bid(chipsLeft):
    try:
        count = int(input("How many chips to bid(1-%d)?" % chipsLeft))
        if count <= chipsLeft and count > 0:
            return count
    except:
        pass
    print("Invalid number of chips")
    return get_bid(chipsLeft)
        

def display_details():
    global i
    print("Rounds of game played:%d" % (index - i))
    win = 0
    lose = 0
    size = len(result)
    for i in range(0, size):
        ele = result[i]
        sign = result_sign[i]
        notice = ""
        if not sign:
            notice = "you lost"
            lose += 1
        else: 
            notice = "you won"
            win += 1
        print("Round %d:You bid %d chips and " % (i + 1, ele) + notice)
    print(("Round won:%d/Round lost:%d,Winning rate:%.1f" % (win, lose, win/size * 100)) + "%," + notice)
    print("Number of choice left:%d" % now_total)
    print("Thank you for playing")
    
def get_choice():
    choice = input("Left or Right[l, r]")
    if choice == "l" or choice == "r":
        return choice
    print("Invalid choice!")
    return get_choice()
    
def get_yn():
    choice = input("Continue playing[y, n]")
    if choice == "y" or choice == "n":
        return choice
    print("Invalid choice!")
    return get_yn()
    
def play_dodge():
    global i
    global now_total
    choice_arr = ["l", "r"]
    while i > 0:
        count = get_bid(now_total)
        choice = get_choice()
        print("You chose:" + choice)
        computer = choice_arr[random.randint(0, 1)]
        print("Computer chose:" + computer)
        result.append(count)
        if computer != choice:
            print("You lose!")
            now_total -= count
            result_sign.append(False)
        else: 
            print("You win!")
            now_total += count
            result_sign.append(True)
        if now_total == 1:
            print("You now have only one chip left!")
        else:
            print("You now have %d chips" % now_total)
        i = i -1
        if i == 0:
            display_details()
            break
        yn = get_yn()
        if yn == "n":
            display_details()
            break
        print("You are biding one last chip!")
        
if __name__ == '__main__':
    play_dodge()
