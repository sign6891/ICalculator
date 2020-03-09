package com.example.icalculator.theme;

import android.util.Log;

import java.util.Stack;

public class ThemeChange {

    double finalResult;
    public boolean flagDivideZiro = false;

    public double calculate(String input) {
        String output = getExpression(input); //Преобразовываем выражение в постфиксную запись
        finalResult = counting(output); //Решаем полученное выражение
        return finalResult; //Возвращаем результат
    }

    private String getExpression(String minput) {
        String output = new String(); //Строка для хранения выражения
        Stack<Character> operStack = new Stack<>(); //Стек для хранения операторов

        char[] input = minput.toCharArray();
        for (int i = 0; i < input.length; i++) //Для каждого символа в входной строке
        {
            //Разделители пропускаем
            if (isDelimeter(input[i]))
                continue; //Переходим к следующему символу

            //Если символ - цифра, то считываем все число
            if (Character.isDigit(input[i]) || (input[i] == '-' && Character.isDigit(input[i + 1]))) //Если цифра
            {
                //Читаем до разделителя или оператора, что бы получить число
                while (!isDelimeter(input[i])) {
                    output += input[i]; //Добавляем каждую цифру числа к нашей строке
                    i++; //Переходим к следующему символу

                    if (i == input.length) break; //Если символ - последний, то выходим из цикла
                }

                output += " "; //Дописываем после числа пробел в строку с выражением
                i--; //Возвращаемся на один символ назад, к символу перед разделителем
            }

            //Если символ - оператор
            if (isOperator(input[i])) //Если оператор
            {
                if (operStack.size() > 0) //Если в стеке есть элементы
                    if (getPriority(input[i]) <= getPriority(operStack.peek())) //И если приоритет нашего оператора меньше или равен приоритету оператора на вершине стека
                        output += operStack.pop().toString() + " "; //То добавляем последний оператор из стека в строку с выражением

                //Если стек пуст, или же приоритет оператора выше - добавляем операторов на вершину стека
                operStack.push(input[i]);
            }
        }

        //Когда прошли по всем символам, выкидываем из стека все оставшиеся там операторы в строку
        while (operStack.size() > 0)
            output += operStack.pop() + " ";

        return output; //Возвращаем выражение в постфиксной записи
    }


    private double counting(String minput) {
        double result = 0; //Результат
        Stack<Double> temp = new Stack<Double>(); //стек для решения

        char[] input = minput.toCharArray();
        
        for (int i = 0; i < input.length; i++) //Для каждого символа в строке
        {
            //Если символ - цифра, то читаем все число и записываем на вершину стека
            if (Character.isDigit(input[i]) || (input[i] == '-' && Character.isDigit(input[i + 1]))) {
                String a = new String();

               // if (Character.isDigit(input[i]) && input[i - 1] == '-') {
               //     flag = false;
               // }
                while (!isDelimeter(input[i])) //Пока не разделитель
                {
                    a += input[i]; //Добавляем
                    i++;
                    if (i == input.length) break;
                }
               // if (!flag) {
               //     temp.push(-1 * Double.parseDouble(a));
               //     flag = true;
               // } else {
                    temp.push(Double.parseDouble(a)); //Записываем в стек
               // }
                i--;
            } else if (isOperator(input[i]) && !isOperator(input[i + 1])) //Если символ - оператор
            {
                //Берем два последних значения из стека
                double a = temp.pop();
                double b = temp.pop();

                switch (input[i]) //И производим над ними действие, согласно оператору
                {
                    case '+':
                        result = b + a;
                        break;
                    case '-':
                        result = b - a;
                        break;
                    case 'x':
                        result = b * a;
                        break;
                    case '/':
                        if (a != 0 && b != 0) {
                            result = b / a;
                        } else {
                            flagDivideZiro = true;
                        }
                        break;
                }
                temp.push(result); //Результат вычисления записываем обратно в стек
            }
        }
        return temp.peek(); //Забираем результат всех вычислений из стека и возвращаем его
    }


    private boolean isDelimeter(char c) {
        if ((" =".indexOf(c) != -1))
            return true;
        return false;
    }

    private boolean isOperator(char с) {
        if (("+-x/".indexOf(с) != -1))
            return true;
        return false;
    }

    private byte getPriority(char s) {
        switch (s) {
            case '+':
                return 0;
            case '-':
                return 1;
            case 'x':
                return 2;
            case '/':
                return 3;
            default:
                return 4;
        }
    }
}
