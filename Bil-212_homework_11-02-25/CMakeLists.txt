cmake_minimum_required(VERSION 3.30)
project(212_homework)

set(CMAKE_CXX_STANDARD 17)

add_executable(212_homework main.cpp)

target_link_libraries(212_homework winmm)