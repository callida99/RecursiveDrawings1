import java.util.Scanner;

public class percentage {

    public static char[][] map;

    public static void initalizeMap()
    {
        String[] mapStringArray =
                {"         ,__                                                  _,  ",
                        "      \\~\\|  ~~---___              ,                          | \\  ",
                        "       |      / |   ~~~~~~~|~~~~~| ~~---,                  _/,  > ",
                        "      /~-_--__| |          |     \\     / ~\\~~/          /~| ||,'  ",
                        "      |       /  \\         |------|   {    / /~)     __-  ',|_\\,  ",
                        "     /       |    |~~~~~~~~|      \\    \\   | | '~\\  |_____,|~,-'  ",
                        "     |~~--__ |    |        |____  |~~~~~|--| |__ /_-'     {,~     ",
                        "     |   |  ~~~|~~|        |    ~~\\     /  `-' |`~ |~_____{/      ",
                        "     |   |     |  '---------,      \\----|   |  |  ,' ~/~\\,|`      ",
                        "     ',  \\     |    |       |~~~~~~~|    \\  | ,'~~\\  /    |       ",
                        "      |   \\    |    |       |       |     \\_-~    /`~___--\\       ",
                        "      ',   \\  ,-----|-------+-------'_____/__----~~/      /       ",
                        "       '_   '\\|     |      |~~~|     |    |      _/-,~~-,/        ",
                        "         \\    |     |      |   |_    |    /~~|~~\\    \\,/          ",
                        "          ~~~-'     |      |     `~~~\\___|   |   |    /           ",
                        "              '-,_  | _____|          |  /   | ,-'---~\\           ",
                        "                  `~'~  \\             |  `--,~~~~-~~,  \\          ",
                        "                         \\/~\\      /~~~`---`         |  \\         ",
                        "                             \\    /                   \\  |        ",
                        "                              \\  |                     '\\'        ",
                        "                               `~'                                "};
        map = new char[21][75];
        for (int r = 0; r < mapStringArray.length; r++)
            for (int c = 0; c < mapStringArray[r].length(); c ++)
                map[r][c] = mapStringArray[r].charAt(c);
        System.out.println("Map initialized");
    }

    public static void printMap()
    {
        for (char[] row: map)
        {
            for (char col: row)
                System.out.print(col);
            System.out.println();
        }
        System.out.println();
    }

    public static void main (String[] args)
    {
        initalizeMap();
        int x,y;
        Scanner in = new Scanner(System.in);
        do
        {
            printMap();
            System.out.println("Type an row and column to fill, or -1 -1 to end.");
            x = in.nextInt();
            y = in.nextInt();
            if (x >= 0 && y >= 0)
                floodFill(x,y);
        }
        while (x >= 0 && y >= 0);
    }

    //precondition: x and y are both non-negative
    //postcondition: floodfill the space bounding the point (x,y) with # characters.
    public static void floodFill(int x, int y)
    {
            if(x<0||y<0||x>map.length-1||y>map[0].length-1)
                return;
            if(map[x][y]!=' ')
                return;
            map[x][y] = '*';
            floodFill(x-1,y);
            floodFill(x+1,y);
            floodFill(x,y-1);
            floodFill(x,y+1);
    }
}
