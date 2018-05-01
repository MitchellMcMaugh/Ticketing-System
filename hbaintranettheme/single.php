<?php
/**
 * The template for displaying all single posts and attachments
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

	<div class="lhs-menu">
	<?php if ( has_nav_menu( 'news-menu' ) || has_nav_menu( 'social' ) ) : ?>
			<div class="menu">
				<?php if ( has_nav_menu( 'news-menu' ) ) : ?>
					<?php
						wp_nav_menu( array(
							'theme_location' => 'news-menu',
							'menu_class'     => 'news-menu',
						 ) );
					?>
				<?php endif; ?>
			</div>
		<?php endif; ?>
	</div>

	<div class="center-content">

		<?php if ( have_posts() ) : ?>
			<?php
			// Start the Loop.
			while ( have_posts() ) : the_post();
				/*
				 * Include the Post-Format-specific template for the content.
				 * If you want to override this in a child theme, then include a file
				 * called content-___.php (where ___ is the Post Format name) and that will be used instead.
				 */
				get_template_part( 'template-parts/content', 'single' );
			// End the loop.

				
				
			endwhile;
		// If no content, include the "No posts found" template.
		else :
			get_template_part( 'template-parts/content', 'none' );
		endif;
		?>



	</div>

	<div class="rhs-content">
		
	</div>


</div>

<?php get_footer(); ?>
