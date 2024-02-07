import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        System.out.print("Enter a title search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {
        System.out.print("Enter an actor/actress' name: ");
        String searchActor = scanner.nextLine();

        searchActor = searchActor.toLowerCase();

        ArrayList<String> cast = new ArrayList<>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String actor = movies.get(i).getCast();
            actor = actor.toLowerCase();
            String[] actorList = actor.split("\\|");

            for (String actors : actorList) {
                if ((actors.contains(searchActor)) && !(cast.contains(actors)))
                {
                    cast.add(actors);
                }
            }
        }

        for (int m = 1; m < cast.size(); m++)
        {
            String temp = cast.get(m);
            String tempTitle = temp;

            int possibleIndex = m;
            while (possibleIndex > 0 && tempTitle.compareTo(cast.get(possibleIndex - 1)) < 0)
            {
                cast.set(possibleIndex, cast.get(possibleIndex - 1));
                possibleIndex--;
            }
            cast.set(possibleIndex, temp);
        }

        // now, display them all to the user
        for (int j = 0; j < cast.size(); j++)
        {
            String castName = cast.get(j);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = j + 1;

            System.out.println("" + choiceNum + ". " + castName );
        }

        System.out.println("Which Actor would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String selectedActor = cast.get(choice - 1);

        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int l = 0; l < movies.size(); l++)
        {
            String movieCast = movies.get(l).getCast();
            movieCast = movieCast.toLowerCase();

            if (movieCast.contains(selectedActor))
            {
                results.add(movies.get(l));
            }
        }

        // sort the results by title
        sortResults(results);

        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int secondChoice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(secondChoice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();


    }

    private void searchKeywords()
    {
        System.out.print("Enter a keyWord term: ");
        String searchKeyWord = scanner.nextLine();

        // prevent case sensitivity
        searchKeyWord = searchKeyWord.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keyWord = movies.get(i).getKeywords();
            keyWord = keyWord.toLowerCase();

            if (keyWord.indexOf(searchKeyWord) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

    }

    private void listGenres() {
        ArrayList<String> genres = new ArrayList<>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String genre = movies.get(i).getGenres();
            genre = genre.toLowerCase();
            String[] genreList = genre.split("\\|");

            for (String g : genreList) {
                if (!(genres.contains(g)))
                {
                    genres.add(g);
                }
            }
        }

        for (int m = 1; m < genres.size(); m++)
        {
            String temp = genres.get(m);
            String tempTitle = temp;

            int possibleIndex = m;
            while (possibleIndex > 0 && tempTitle.compareTo(genres.get(possibleIndex - 1)) < 0)
            {
                genres.set(possibleIndex, genres.get(possibleIndex - 1));
                possibleIndex--;
            }
            genres.set(possibleIndex, temp);
        }

        // now, display them all to the user
        for (int j = 0; j < genres.size(); j++)
        {
            String genreName = genres.get(j);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = j + 1;

            System.out.println("" + choiceNum + ". " + genreName);
        }

        System.out.println("Which genre would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        String selectedGenre = genres.get(choice - 1);

        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int l = 0; l < movies.size(); l++)
        {
            String movieGenre = movies.get(l).getGenres();
            movieGenre = movieGenre.toLowerCase();

            if (movieGenre.contains(selectedGenre))
            {
                results.add(movies.get(l));
            }
        }

        // sort the results by title
        sortResults(results);

        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int secondChoice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(secondChoice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();

    }

    private void listHighestRated()
    {
        ArrayList<Movie> results = new ArrayList<Movie>();

        for (int i = 0; i <= movies.size(); i++){
            double highestRating = 10.0;
            if(movies.get(i).getUserRating() == highestRating){
                results.add(movies.get(i));
                movies.remove(movies.get(i));
            }
        }

    }

    private void listHighestRevenue()
    {

    }

    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}