<?php
/**
 * The template for displaying pages
 *
 * This is the template that displays all pages by default.
 * Please note that this is the WordPress construct of pages and that
 * other "pages" on your WordPress site will use a different template.
 *
 * @package WordPress
 * @subpackage HBA_Intranet_Theme
 * @since HBA Intranet Theme 1.0
 */

get_header(); ?>

<div class="main-content">

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

		<div class="content-head">
			<h2>Page Title ykj</h2>
		</div>

		<!-- Begin posts -->
		<div class="content-middle">
			<?php
			// Start the loop.
			while ( have_posts() ) : the_post();

				// Include the page content template.
				get_template_part( 'template-parts/content', 'page' );

				// If comments are open or we have at least one comment, load up the comment template.
				if ( comments_open() || get_comments_number() ) {
					comments_template();
				}

				// End of the loop.
			endwhile;
			?>
		</div>
		<!-- End posts -->

	</div>

	<div class="rhs-content">
		
	</div>


</div>



<?php get_footer(); ?>
