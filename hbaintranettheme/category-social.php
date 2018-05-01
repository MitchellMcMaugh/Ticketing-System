<?php
/**
 * The template for displaying archive pages
 *
 * Used to display archive-type pages if nothing more specific matches a query.
 * For example, puts together date-based pages if no date.php file exists.
 *
 * If you'd like to further customize these archive views, you may create a
 * new template file for each one. For example, tag.php (Tag archives),
 * category.php (Category archives), author.php (Author archives), etc.
 *
 * @link https://codex.wordpress.org/Template_Hierarchy
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

		<div class="content-head">
			<h2>Social</h2>
		</div>

		<!-- Begin posts -->
		<div class="content-middle">

			<?php if ( have_posts() ) : ?>
				<?php
				// Start the Loop.
				while ( have_posts() ) : the_post();
					/*
					 * Include the Post-Format-specific template for the content.
					 * If you want to override this in a child theme, then include a file
					 * called content-___.php (where ___ is the Post Format name) and that will be used instead.
					 */
					get_template_part( 'template-parts/content', 'news' );
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
		<div class="news-calendar">
			<div class="news-title">
				<h2>Calendar</h2>
				<a class="more" href="<?php echo esc_url( home_url( '/' ) ); ?>events/category/all/">More events</a>
			</div>
			<?php dynamic_sidebar( 'upcoming-events' ); ?>
		</div>
	</div>


</div>

<?php get_footer(); ?>
