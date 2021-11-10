import java.util.*;
public class yodaCalc
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter your expression");
        String input = in.nextLine();
        input = input.replace(" ", "");
        System.out.print("Expression: ");
        System.out.println(infixToPostfix(input)); //Print the Postfix expression from the method
        String postfix = infixToPostfix(input); //Store postfix in string var
        System.out.println(evaluatePostfix(postfix)); //Print the evaluation with the postfix
    }
    public static String infixToPostfix(String input)
    {
        String result = ""; //Empty String to add postfix expression
        Stack<Character> holder = new Stack<Character>(); //Holding Stack
        //Loop through every item in the input
        for(int i = 0; i < input.length(); i++)
        {
            char c = input.charAt(i);
            if(Character.isLetterOrDigit(c))
            {
                result = result + c; //Add char to the result string
            }
            else if(c == '(') // Character is the start of a parentheses 
            {
                holder.push(c);
            }
            else if (c == ')') // Character is ending the parentheses
            {
                while (!holder.isEmpty() && holder.peek() != '(')
                {
                    result +=holder.pop();
                }
                holder.pop();
            }
            else // Character is an operator
            {
                while (!holder.isEmpty() && priority(c)
                         <= priority(holder.peek())){
                   
                    result += holder.pop();
             }
                holder.push(c);
            }
        }
        // pop all the operators from the stack
        while (!holder.isEmpty())
        {
            if(holder.peek() == '(')
            {
                return "Invalid Expression";
            }
            result = result + holder.pop();
         }
        return result;
    }
    //Evaluates the priority of the char
    public static int priority(char c)
    {
        //Scans if the char is equal to +-/*^
        switch (c)
        {
        case '+':
        case '-':
            return 1;
      
        case '*':
        case '/':
            return 2;
      
        case '^':
            return 3;
        }
        return -1;
    }
    public static int evaluatePostfix(String inputPostfix)
    {
        Stack<Integer> holder = new Stack<Integer>(); //Holding Stack
        // Loop through each char in the postfix expression
        for(int i=0;i<inputPostfix.length();i++)
        {
            char c=inputPostfix.charAt(i);
             
            // If the scanned character is an operand (number here),
            // push it to the stack.
            if(Character.isDigit(c))
            holder.push(c - '0');
             
            //  If the scanned character is an operator, pop two
            // elements from stack apply the operator
            else
            {
                int val1 = holder.pop();
                int val2 = holder.pop();
                 
                switch(c)
                {
                    case '+':
                    holder.push(val2+val1);
                    break;
                     
                    case '-':
                    holder.push(val2- val1);
                    break;
                     
                    case '/':
                    holder.push(val2/val1);
                    break;
                     
                    case '*':
                    holder.push(val2*val1);
                    break;
              }
            }
        }
        return holder.pop();
    }
}