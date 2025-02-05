#include <iostream>
#include <windows.h>
#include <mmsystem.h>

#pragma comment(lib, "winmm.lib")


class ToyPhone {
private:
    int batteryPower = 10;
    bool isWorking(){
        return batteryPower>0;
    }
public:
    std::string color;
    float displaySize;
    float weight;
    std::string batteryType;

    void sing(){
        if(isWorking()){
        mciSendString("open \"music.mp3\" type mpegvideo alias mymusic", nullptr, 0, nullptr);
        std::cout<<"Press Enter to stop"<<std::endl;
        mciSendString("play mymusic", nullptr, 0, nullptr);

        system("pause");

        mciSendString("close mymusic", nullptr, 0, nullptr);
            batteryPower--;
        }
        else
            std::cout<<"Low battery power, please change" << std::endl;
    }

    void changeBattery(){
        batteryPower = 10;
        std::cout<< "Battery successfully changed!"<<std::endl;
    }

    void printInfo(){
        std::cout<<"Display size: " << displaySize<<" inches"<<std::endl;
        std::cout<<"Color: " << color << std::endl;
        std::cout<<"Weight: " << weight <<"g"<<std::endl;
        std::cout<<"Battery type: " << batteryType<<std::endl;
    }

};


int main() {
    ToyPhone toyPhone;

   // toyPhone.sing();

    toyPhone.batteryType = "1.5V battery";
    toyPhone.color = "red";
    toyPhone.weight = 50;
    toyPhone.displaySize = 2.5f;

  //  std::cout<<toyPhone.displaySize<<std::endl;
    ToyPhone newOne;

    newOne.displaySize = 3.3f;
    newOne.batteryType = "9V Big battery";
    newOne.weight = 87.7f;
    newOne.color = "Yellow";
    std::cout<<"-----------------------------"<<std::endl;
    toyPhone.printInfo();

    std::cout<<"-----------------------------"<<std::endl;
    newOne.printInfo();

    newOne = toyPhone;
    std::cout<<"-----------------------------"<<std::endl;
    newOne.printInfo();
    return 0;
}



