<?php
/**
 * The template for the content bottom widget areas on posts and pages
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */

?>

<?php $melt = array("from-meltwater");

if ( have_posts() ) : ?>
	<?php
	// Start the Loop.
	while ( have_posts() ) : the_post();
		if(!in_category($melt)) continue;
		/*
		 * Include the Post-Format-specific template for the content.
		 * If you want to override this in a child theme, then include a file
		 * called content-___.php (where ___ is the Post Format name) and that will be used instead.
		 */
		get_template_part( 'template-parts/content', 'meltwater' );
	// End the loop.
	endwhile;
// If no content, include the "No posts found" template.
else :
	get_template_part( 'template-parts/content', 'none' );
endif;
?>
