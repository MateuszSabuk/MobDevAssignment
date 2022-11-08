# Mobile Development Small Project
### My Little Library

#### Due date is 9th November

#### Group is made by Mateusz Sabuk (62540) and Arthur Wilhelm Strømme Kristiansen (62255)


## Used technologies
-  **Room** or **SQLite** as the database?
	- [Documentation](https://developer.android.com/training/data-storage/room)
- **Android Studio** bar-code scanner
	- [Tutorial](https://medium.com/analytics-vidhya/creating-a-barcode-scanner-using-android-studio-71cff11800a2)
	- [YT Tutorial](https://www.google.com/url?sa=t&rct=j&q=&esrc=s&source=web&cd=&cad=rja&uact=8&ved=2ahUKEwjEio7Eu_36AhUOrxoKHU6_CsMQwqsBegQIChAB&url=https%3A%2F%2Fwww.youtube.com%2Fwatch%3Fv%3DjtT60yFPelI&usg=AOvVaw3XmFqNFO90jSS4tHEH1YVo) didn't check yet
- **API**
	- [Google Books](https://developers.google.com/books/docs/v1/using) ?

### ! Decided to go with "Shelf" as a book collection name

## Layout
- **Main Activity**
    - *Shelves Fragment*
        - View all Shelves
            Clicking on a shelf should open another fragment or activity with all books saved in the shelf
            - View books in the shelf
                Clicking on a book should open another fragment or activity with book data, available edition or deletion of the record
            - Add new books to the shelf
            - Remove books from the shelf
        - Add new Shelf
    - *Scanner Fragment*
        - Scanner
            After scan and successful data fetch from API opens "Add Book Activity" with the imported data that can be changed
        -  Button to manually input scanner activity
            After click opens "Add Book Activity" without any data default data
    - *History Fragment*
        - View all adds history
- **Add Book Activity**
    - Details needed
        - book name
        - genre
        - ISBN
        - have you read it
        - score
        - To which shelves should it be added to?
    - add the book to the local db of books
- **Show Book Activity** // not created yet
    - All book data


Source:
https://stackoverflow.com/questions/41774963/android-seekbar-show-progress-value-along-the-seekbar

