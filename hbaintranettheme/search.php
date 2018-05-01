<?php
/**
 * The template for displaying search results pages
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */

get_header(); ?>

<div class="main-content">

	<?php if ( function_exists('yoast_breadcrumb') ) {
		yoast_breadcrumb('<div class="bread">','</div>');
	} ?>

	<div class="lhs-menu app">

	</div>

	<div class="center-content">

		<div class="content-head">
			<?php if ( have_posts() ) : ?>
				<h2><?php printf( __( 'Search Results for: %s', 'hbaintranettheme' ), '<span>' . esc_html( get_search_query() ) . '</span>' ); ?></h2>
		</div>

		<!-- Begin posts -->
		<div class="content-middle">

			
				<?php
				// Start the Loop.
				while ( have_posts() ) : the_post();
					/*
					 * Include the Post-Format-specific template for the content.
					 * If you want to override this in a child theme, then include a file
					 * called content-___.php (where ___ is the Post Format name) and that will be used instead.
					 */
					get_template_part( 'template-parts/content', 'search' );
				// End the loop.
				endwhile;

				// Previous/next page navigation.
				the_posts_pagination( array(
					'prev_text'          => __( 'Previous page', 'hbaintranettheme' ),
					'next_text'          => __( 'Next page', 'hbaintranettheme' ),
					'before_page_number' => '<span class="meta-nav screen-reader-text">' . __( 'Page', 'hbaintranettheme' ) . ' </span>',
				) );

			// If no content, include the "No posts found" template.
			else :
				get_template_part( 'template-parts/content', 'none' );
			endif;
			?>

		</div>
		<!-- End posts -->

	</div>

	<div class="rhs-content">
		
	</div>


</div>

<?php get_footer(); ?>

















