<?php
/**
 * Custom HBA Intranet Theme template tags
 *
 * Eventually, some of the functionality here could be replaced by core features.
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */

if ( ! function_exists( 'hbaintranettheme_entry_meta' ) ) :
/**
 * Prints HTML with meta information for the categories, tags.
 *
 * Create your own hbaintranettheme_entry_meta() function to override in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 */
function hbaintranettheme_entry_meta() {
	

	if ( 'post' === get_post_type() ) {
		$author_avatar_size = apply_filters( 'hbaintranettheme_author_avatar_size', 102 );
		printf( '<div class="news-item-top">%1$s<div class="news-item-meta"><h5><span class="screen-reader-text">%2$s </span> <a class="url fn n" href="%3$s">%4$s</a>',
			get_avatar( get_the_author_meta( 'user_email' ), $author_avatar_size ),
			_x( '', 'Used before post author name.', 'hbaintranettheme' ),
			esc_url( get_author_posts_url( get_the_author_meta( 'ID' ) ) ),
			get_the_author()
		);
	}

	if ( 'post' === get_post_type() ) {
		hbaintranettheme_entry_taxonomies();
	}

	if ( in_array( get_post_type(), array( 'post', 'attachment' ) ) ) {
		hbaintranettheme_entry_date();
	}


}
endif;

if ( ! function_exists( 'hbaintranettheme_entry_date' ) ) :
/**
 * Prints HTML with date information for current post.
 *
 * Create your own hbaintranettheme_entry_date() function to override in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 */
function hbaintranettheme_entry_date() {
	$time_string = '<time class="entry-date published updated" datetime="%1$s">%2$s</time>';

	if ( get_the_time( 'U' ) !== get_the_modified_time( 'U' ) ) {
		$time_string = '<time class="updated" datetime="%3$s">%4$s</time>';
	}

	$time_string = sprintf( $time_string,
		esc_attr( get_the_date( 'c' ) ),
		get_the_date(),
		esc_attr( get_the_modified_date( 'c' ) ),
		get_the_modified_date()
	);

	printf( '<small class="posted-on"><span class="screen-reader-text">%1$s </span><a href="%2$s" rel="bookmark">' . get_the_date() . '</a></small></div></div>',
		_x( '', 'Used before publish date.', 'hbaintranettheme' ),
		esc_url( get_permalink() ),
		$time_string
	);
}
endif;

if ( ! function_exists( 'hbaintranettheme_entry_taxonomies' ) ) :
/**
 * Prints HTML with category and tags for current post.
 *
 * Create your own hbaintranettheme_entry_taxonomies() function to override in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 */
function hbaintranettheme_entry_taxonomies() {
	$categories_list = get_the_category_list( _x( ', ', 'Used between list items, there is a space after the comma.', 'hbaintranettheme' ) );
	if ( $categories_list && hbaintranettheme_categorized_blog() ) {
		printf( '<span class="cat-links"><span>%1$s </span>%2$s</span></h5>',
			_x( ' in ', 'Used before category names.', 'hbaintranettheme' ),
			$categories_list
		);
	}

	$tags_list = get_the_tag_list( '', _x( ', ', 'Used between list items, there is a space after the comma.', 'hbaintranettheme' ) );
	if ( $tags_list ) {
		printf( '<span class="tags-links"><span class="screen-reader-text">%1$s </span>%2$s</span>',
			_x( 'Tags', 'Used before tag names.', 'hbaintranettheme' ),
			$tags_list
		);
	}
}
endif;

if ( ! function_exists( 'hbaintranettheme_post_thumbnail' ) ) :
/**
 * Displays an optional post thumbnail.
 *
 * Wraps the post thumbnail in an anchor element on index views, or a div
 * element when on single views.
 *
 * Create your own hbaintranettheme_post_thumbnail() function to override in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 */
function hbaintranettheme_post_thumbnail() {
	if ( post_password_required() || is_attachment() || ! has_post_thumbnail() ) {
		return;
	}

	if ( is_singular() ) :
	?>

	<div class="post-thumbnail">
		<?php the_post_thumbnail(); ?>
	</div><!-- .post-thumbnail -->

	<?php else : ?>

	<a class="post-thumbnail" href="<?php the_permalink(); ?>" aria-hidden="true">
		<?php the_post_thumbnail( 'post-thumbnail', array( 'alt' => the_title_attribute( 'echo=0' ) ) ); ?>
	</a>

	<?php endif; // End is_singular()
}
endif;

if ( ! function_exists( 'hbaintranettheme_excerpt' ) ) :
	/**
	 * Displays the optional excerpt.
	 *
	 * Wraps the excerpt in a div element.
	 *
	 * Create your own hbaintranettheme_excerpt() function to override in a child theme.
	 *
	 * @since HBA Intranet Theme 1.0
	 *
	 * @param string $class Optional. Class string of the div element. Defaults to 'entry-summary'.
	 */
	function hbaintranettheme_excerpt( $class = 'entry-summary' ) {
		$class = esc_attr( $class );

		if ( has_excerpt() || is_search() ) : ?>
			<div class="<?php echo $class; ?>">
				<?php the_excerpt(); ?>
			</div><!-- .<?php echo $class; ?> -->
		<?php endif;
	}
endif;

if ( ! function_exists( 'hbaintranettheme_excerpt_more' ) && ! is_admin() ) :
/**
 * Replaces "[...]" (appended to automatically generated excerpts) with ... and
 * a 'Continue reading' link.
 *
 * Create your own hbaintranettheme_excerpt_more() function to override in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @return string 'Continue reading' link prepended with an ellipsis.
 */
function hbaintranettheme_excerpt_more() {
	$link = sprintf( '<a href="%1$s" class="more-link">%2$s</a>',
		esc_url( get_permalink( get_the_ID() ) ),
		/* translators: %s: Name of current post */
		sprintf( __( 'Continue reading<span class="screen-reader-text"> "%s"</span>', 'hbaintranettheme' ), get_the_title( get_the_ID() ) )
	);
	return ' &hellip; ' . $link;
}
add_filter( 'excerpt_more', 'hbaintranettheme_excerpt_more' );
endif;

/**
 * Determines whether blog/site has more than one category.
 *
 * Create your own hbaintranettheme_categorized_blog() function to override in a child theme.
 *
 * @since HBA Intranet Theme 1.0
 *
 * @return bool True if there is more than one category, false otherwise.
 */
function hbaintranettheme_categorized_blog() {
	if ( false === ( $all_the_cool_cats = get_transient( 'hbaintranettheme_categories' ) ) ) {
		// Create an array of all the categories that are attached to posts.
		$all_the_cool_cats = get_categories( array(
			'fields'     => 'ids',
			// We only need to know if there is more than one category.
			'number'     => 2,
		) );

		// Count the number of categories that are attached to the posts.
		$all_the_cool_cats = count( $all_the_cool_cats );

		set_transient( 'hbaintranettheme_categories', $all_the_cool_cats );
	}

	if ( $all_the_cool_cats > 1 ) {
		// This blog has more than 1 category so hbaintranettheme_categorized_blog should return true.
		return true;
	} else {
		// This blog has only 1 category so hbaintranettheme_categorized_blog should return false.
		return false;
	}
}

/**
 * Flushes out the transients used in hbaintranettheme_categorized_blog().
 *
 * @since HBA Intranet Theme 1.0
 */
function hbaintranettheme_category_transient_flusher() {
	if ( defined( 'DOING_AUTOSAVE' ) && DOING_AUTOSAVE ) {
		return;
	}
	// Like, beat it. Dig?
	delete_transient( 'hbaintranettheme_categories' );
}
add_action( 'edit_category', 'hbaintranettheme_category_transient_flusher' );
add_action( 'save_post',     'hbaintranettheme_category_transient_flusher' );
