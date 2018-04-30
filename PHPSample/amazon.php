#!/usr/local/bin/php

<?
/*
 * amazon.php - Amazon search using Amazon's SOAP API.
 * 
 * This is a sample program. It illustrates the use of the
 * AmazonSearch object. If the program is invoked without
 * any command-line arguments then it performs one or two
 * searches (single and multiple argument) of each type.
 * If invoked with one or more of the following arguments
 * then it performs only the requested searches:
 *
 * -a	Author search
 * -b	BrowseNode search
 * -c   Actor search
 * -d 	Director search
 * -e   Seller Profile search
 * -i   Similarity search
 * -k	Keyword search
 * -l   ListMania! search
 * -m	Manufacturer search
 * -p   Power search
 * -s 	ASIN search
 * -q   Seller search
 * -t   Artist search
 * -u	UPC search
 * -x   Exchange search
 *
 * The following options affect the type of search and the amount
 * of output:
 *
 * -h 	Do heavy search
 * -r   Print all returned data
 * -z   Turn on AmazonSearch debugging
 * 
 * Use the Amazon SOAP APIs to fetch a lists of products that
 * match various attributes.
 */

/* Run forever */
set_time_limit(0);

/* Load Amazon Search object */
require("./AmazonSearch.php");

/* Do a bunch of sample searches */
main($GLOBALS['argv']);

function main($argv)
{
  $DoHeavySearch = false;
  $PrintAll      = false;
  $Debug         = false;

  $SearchOptions = array();

  if (count($argv) == 1)
  {
    $SearchOptions['a'] = true;		/* Author 		*/
    $SearchOptions['b'] = true;		/* BrowseNode 		*/
    $SearchOptions['c'] = true;		/* Actor		*/
    $SearchOptions['d'] = true;		/* Director		*/
    $SearchOptions['e'] = true;		/* Seller Profile	*/
    $SearchOptions['i'] = true;		/* Similarity		*/
    $SearchOptions['k'] = true;		/* Keyword 		*/
    $SearchOptions['l'] = true;		/* ListMania!		*/
    $SearchOptions['m'] = true;		/* Manufacturer		*/
    $SearchOptions['p'] = true;		/* Power		*/
    $SearchOptions['q'] = true;		/* Seller		*/
    $SearchOptions['s'] = true;		/* ASIN			*/
    $SearchOptions['t'] = true;		/* Artist		*/
    $SearchOptions['u'] = true;		/* UPC		 	*/
    $SearchOptions['x'] = true;		/* Exchange	 	*/
  }
  else
  {
    foreach ($argv as $arg)
    {
      switch ($arg)
      {
	case '-a':
          $SearchOptions['a'] = true;	/* Author 		*/
  	  break;

	case '-b':
          $SearchOptions['b'] = true;	/* BrowseNode 		*/
  	  break;

	case '-c':
          $SearchOptions['c'] = true;	/* Actor		*/
  	  break;

	case '-d':
          $SearchOptions['d'] = true;	/* Director		*/
  	  break;

	case '-e':
          $SearchOptions['e'] = true;	/* Seller profile	*/
  	  break;

	case '-h':
	  $DoHeavySearch = true;	/* Do heavy search	*/
   	  break;

	case '-i':
          $SearchOptions['i'] = true;	/* Similarity		*/
  	  break;

	case '-k':
	  $SearchOptions['k'] = true;	/* Keyword 		*/
  	  break;

	case '-l':
          $SearchOptions['l'] = true;	/* ListMania!		*/
  	  break;

	case '-m':
          $SearchOptions['m'] = true;	/* Manufacturer		*/
  	  break;

	case '-p':
          $SearchOptions['p'] = true;	/* Power		*/
  	  break;

	case '-q':
          $SearchOptions['q'] = true;	/* Seller		*/
  	  break;

	case '-r':
	  $PrintAll = true;		/* Print all data 	*/
   	  break;

	case '-s':
          $SearchOptions['s'] = true;	/* ASIN			*/
  	  break;

	case '-u':
          $SearchOptions['u'] = true;	/* UPC		 	*/
  	  break;

	case '-t':
          $SearchOptions['t'] = true;	/* Artist		*/
  	  break;

	case '-x':
          $SearchOptions['x'] = true;	/* Exchange		*/
  	  break;

	case '-z':
	  $Debug = true;		/* Show request & resp.	*/
   	  break;

	case '-s':
      }
    }
  }

  /* Set search terms */
  $Keyword       = 'PHP';
  $Author        = 'Knuth';
  $Node          = 4;
  $ASIN1         = '0201896850';
  $ASIN2         = '0201896842';
  $UPC1          = '078221408424';
  $Manufacturer1 = 'Sony';
  $Manufacturer2 = 'Canon';
  $Actor         = 'Harrison Ford';
  $Director	 = 'George Lucas';
  $Artist        = 'Madonna';
  $ListManiaList = 'C3LHSMTSR0YR';
  $SimilarTo     = '0743211375';
  $Power         = 'author:Knuth and pubdate:2002';
  $Seller        = 'A196I3L4MR6A5Z';
  $Exchange	 = 'Y01Y2741473Y6491313';

  /* Set Amazon associates tag */
  $Tag   = 'webservices-20';

  /* Set Amazon developer token */
  $Token = 'D2ED5GR7A6RZ7Y';

  /* Create search object */
  $AS = new AmazonSearch($Token, $Tag, $Debug);

  /* Set up search mode */
  $SearchType = $DoHeavySearch ? 'heavy' : 'lite';

  /*
   * Note: If you have your own Associate ID, create the object like this:
   *
   * $AS = new AmazonSearch($Token, 'AssociateID');
   */

  if (IsSet ($SearchOptions['k']))
  {
    /* Keyword Search */
    $Results = $AS->DoKeywordSearch($Keyword, $SearchType, 'books', 30);
    PrintProductDetails("Books Matching $Keyword (By Keyword)", $Results, $PrintAll);
  }


  if (IsSet ($SearchOptions['b']))
  {
    /* Browse Node Search */
    $Results = $AS->DoBrowseNodeSearch($Node, $SearchType, 'books', 30);
    PrintProductDetails("Stuff in Browse Node $Node", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['a']))
  {
    /* Author Search */
    $Results = $AS->DoAuthorSearch($Author, $SearchType, 20);
    PrintProductDetails("Books Matching $Author (By Author)", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['s']))
  {
    /* ASIN Search */
    $Results = $AS->DoASINSearch($ASIN1, $SearchType, 'electronics');
    PrintProductDetails("Items with ASIN $ASIN1", $Results, $PrintAll);

    /* Multiple ASIN Search */
    $Results = $AS->DoASINSearch(array($ASIN1, $ASIN2), $SearchType);
    PrintProductDetails("Items with ASINs $ASIN1 or $ASIN2", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['u']))
  {
    /* Single UPC Search */
    $Results = $AS->DoUPCSearch($UPC1, $SearchType);
    PrintProductDetails("UPC Search for $UPC1", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['m']))
  {
    /* Single Manufacturer Search */
    $Results = $AS->DoManufacturerSearch($Manufacturer1, $SearchType, 'electronics', 100);
    PrintProductDetails("Manufacturer Search for $Manufacturer1", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['c']))
  {
    /* Actor Search */
    $Results = $AS->DoActorSearch($Actor, $SearchType, 'dvd');
    PrintProductDetails("Actor Search for $Actor", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['d']))
  {
    /* Director Search */
    $Results = $AS->DoDirectorSearch($Director, $SearchType, 'dvd');
    PrintProductDetails("Director Search for $Director", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['t']))
  {
    /* Artist Search */
    $Results = $AS->DoArtistSearch($Artist, $SearchType, 'music');
    PrintProductDetails("Music Matching $Artist", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['l']))
  {
    /* ListMania! Search */
    $Results = $AS->DoListManiaSearch($ListManiaList, $SearchType, 20);
    PrintProductDetails("Contents of ListMania! List $ListManiaList", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['i']))
  {
    /* Similarity Search */
    $Results = $AS->DoSimilaritySearch($SimilarTo, $SearchType, 20);
    PrintProductDetails("Items Similar to ASIN $SimilarTo", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['p']))
  {
    /* Power Search */
    $Results = $AS->DoPowerSearch($Power, $SearchType, 'books', 20);
    PrintProductDetails("Power Search For $Power", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['q']))
  {
    /* Seller Search */
    $Results = $AS->DoSellerSearch($Seller, 'open', 'lite', 'books', 20);
    PrintSellerDetails("Details for Seller $Seller", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['e']))
  {
    /* Seller Profile Search */
    $Results = $AS->DoSellerProfileSearch($Seller, 'lite', 20);
    PrintSellerProfileDetails("Details for Seller $Seller", $Results, $PrintAll);
  }

  if (IsSet ($SearchOptions['x']))
  {
    /* Seller Profile Search */
    $Results = $AS->DoExchangeSearch($Exchange, 'lite', 20);
    PrintListingProductDetails("Details for Exchange Item $Exchange", $Results, $PrintAll);
  }
}

/*
 * PrintProductDetails -
 *
 *	Dump the given product details.
 */

function PrintProductDetails($Title, $Result, $PrintAll)
{
  $Count     = count($Result);
  $FullTitle = "$Title - $Count Matches";

  /* Print result title */
  print("$FullTitle\n");
  print(str_repeat('=', strlen($FullTitle)) . "\n");

  /* Show result */
  if ($Result !== null)
  {
    for ($i = 0; $i < count($Result); $i++)
    {
      $ResultRow = $Result[$i];

      //      print_r($ResultRow);

      /*
       * Extract fields of interest.
       * Note: There are many more fields than are shown here.
       */

      $ASIN        = $ResultRow['Asin'];
      $URL         = $ResultRow['Url'];
      $UPC         = $ResultRow['Upc'];
      $Price       = $ResultRow['OurPrice'];
      $ProductName = $ResultRow['ProductName'];

      printf("  %10s %8.8s %10.10s %s\n", $ASIN, $Price, $UPC, $ProductName);
      //      print("  $URL\n\n");

      if ($PrintAll)
      {
	print_r($ResultRow);
      }
    }
  }
  else
  {
    print("  Search failed.\n");
  }
  print("\n");
}
        
/*
 * PrintSellerDetails -
 *
 *	Dump the given seller details.
 */

function PrintSellerDetails($Title, $Result, $PrintAll)
{
  $Count     = count($Result);
  $FullTitle = "$Title - $Count Matches";

  /* Print result title */
  print("$FullTitle\n");
  print(str_repeat('=', strlen($FullTitle)) . "\n");

  /* Show result */
  if ($Result !== null)
  {
    for ($i = 0; $i < count($Result); $i++)
    {
      $ResultRow = $Result[$i];

      /*
       * Extract fields of interest.
       * Note: There are many more fields than are shown here.
       */

      $Nickname      = $ResultRow['SellerNickname'];
      $StoreName     = $ResultRow['StoreName'];
      $OpenListings  = $ResultRow['NumberOfOpenListings'];

      printf("  %-20.20s %-8.8s %9.9d\n", $Nickname, $StoreName, $OpenListings);

      /* Print listing product details */
      $ListingProductInfo    = $ResultRow['ListingProductInfo'];
      $ListingProductDetails = $ListingProductInfo['ListingProductDetails'];

      for ($j = 0; $j < count($ListingProductDetails); $j++)
      {
	$ExchangeID = $ListingProductDetails[$j]['ExchangeId'];
	$ListingID  = $ListingProductDetails[$j]['ListingId'];
	$Title      = $ListingProductDetails[$j]['ExchangeTitle'];
	$Price      = $ListingProductDetails[$j]['ExchangePrice'];

	printf("      %-18.18s %-11.11s %10.10s %s\n",
	       $ExchangeID, $ListingID, $Price, $Title);
      }

      if ($PrintAll)
      {
	print_r($ResultRow);
      }
    }
  }
  else
  {
    print("  Search failed.\n");
  }
  print("\n");
}

/*
 * PrintSellerProfileDetails -
 *
 *	Dump the given seller details.
 */

function PrintSellerProfileDetails($Title, $Result, $PrintAll)
{
  $Count     = count($Result);
  $FullTitle = "$Title - $Count Matches";

  /* Print result title */
  print("$FullTitle\n");
  print(str_repeat('=', strlen($FullTitle)) . "\n");

  /* Show result */
  if ($Result !== null)
  {
    for ($i = 0; $i < count($Result); $i++)
    {
      $ResultRow = $Result[$i];

      /*
       * Extract fields of interest.
       * Note: There are many more fields than are shown here.
       */

      $Nickname        = $ResultRow['SellerNickname'];
      $OverallFeedback = $ResultRow['OverallFeedbackRating'];
      $StoreName       = $ResultRow['StoreName'];

      printf("  %-20.20s %s %s\n", $Nickname, $OverallFeedbackRating, $StoreName);

      /* Print feedback */
      $SellerFeedback = $ResultRow['SellerFeedback'];
      $Feedback       = $SellerFeedback['Feedback'];

      for ($j = 0; $j < count($Feedback); $j++)
      {
	$Rating   = $Feedback[$j]['FeedbackRating'];
	$Comments = $Feedback[$j]['FeedbackComments'];
	$Date     = $Feedback[$j]['FeedbackDate'];
	$Rater    = $Feedback[$j]['FeedbackRater'];

	printf("      %2d %10.10s %-20.20s %s\n",
	       $Rating, $Date, $Rater, $Comments);
      }

      if ($PrintAll)
      {
	print_r($ResultRow);
      }
    }
  }
  else
  {
    print("  Search failed.\n");
  }
  print("\n");
}

/*
 * PrintListingProductDetails -
 *
 *	Dump the given listing product details.
 */

function PrintListingProductDetails($Title, $Result, $PrintAll)
{
  /* Print result title */
  print("$Title\n");
  print(str_repeat('=', strlen($Title)) . "\n");

  /* Show result */
  if ($Result !== null)
  {
    /*
     * Extract fields of interest.
     * Note: There are many more fields than are shown here.
     */

    $ListingID = $Result['ListingId'];
    $Title     = $Result['ExchangeTitle'];
    $Price     = $Result['ExchangePrice'];

    print("$ListingID $Price $Title\n");

    if ($PrintAll)
    {
      print_r($ResultRow);
    }
  }
  else
  {
    print("  Search failed.\n");
  }
  print("\n");
}

