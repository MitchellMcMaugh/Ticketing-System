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
	<div class="home-lhs">
		<div class="home-title">
			<h2>News and insights</h2>
			<a class="more" href="#">More news</a>
		</div>
		<!-- Begin posts -->
		<div class="home-news">
			<?php 
			$myCats = array("from-meltwater");
			query_posts('showposts=6'); if (have_posts()) : while (have_posts()) : the_post(); 
			if(in_category($myCats)) continue;
			?>

				<?php get_template_part( 'template-parts/content', 'home' ); ?>

			<?php endwhile;?>

			<?php else : ?>

				<?php get_template_part( 'template-parts/content', 'none' ); ?>

			<?php endif; wp_reset_query(); ?>
		</div>
		<!-- End posts -->

		<!-- Begin posts -->
		<div class="home-meltwater">
			<h3>From Meltwater</h3>
			<?php 
			$args = array(
				'showposts'=> '3',
				'category_name'=> 'from-meltwater'
			);
			query_posts($args); if (have_posts()) : while (have_posts()) : the_post(); ?>

				<?php get_template_part( 'template-parts/content', 'meltwater' ); ?>
			<?php endwhile;?>

			<?php else : ?>

				<?php get_template_part( 'template-parts/content', 'none' ); ?>

			<?php endif; wp_reset_query(); ?>
		</div>
		<!-- End posts -->
	</div>
	<div class="home-rhs">
		<div class="home-social">
			<div class="social-link">
				<div class="home-title">
					<h2>Social Link</h2>
				</div>
				<?php dynamic_sidebar( 'social-link' ); ?>
				<a class="cta" href="#">Post your event or announcement</a>
			</div>
			<div class="promos">
				Promos
			</div>
		</div>
		<div class="home-calendar">
			<div class="home-title">
				<h2>Calendar</h2>
				<a class="more" href="#">More events</a>
			</div>
			<?php dynamic_sidebar( 'upcoming-events' ); ?>
			<?php dynamic_sidebar( 'out-and-about' ); ?>
		</div>
		<div class="home-people-links">
			<div class="home-people">
				<div class="home-title">
					<h2>People</h2>
					<a class="more" href="#">More people</a>
				</div>
				<div class="users">
					<?php
					$blogusers = get_users( 'blog_id=1' );
					// Array of WP_User objects.
					foreach ( $blogusers as $user ) {
						echo '<a href="' . site_url() . '/author/' . esc_html( $user->user_nicename ) . '">';
						echo '<br><span>' . esc_html( $user->display_name ) . '</span>';
						// echo '<br><span>' . esc_html( $user->user_url ) . '</span>';
						echo '<br><span> ' . esc_html( $user->position ) . '</span>';
						echo '<br><span> ' . esc_html( $user->division ) . '</span>';
						echo '<br><span>T: ' . esc_html( $user->phone ) . '</span>';
						echo ' <span>M: ' . esc_html( $user->mobile ) . '</span>';
						echo get_avatar( $user->ID, 102 );
						echo '</a>';
					}
					?>
				</div>
			</div>
			<div class="home-links">
				<div class="home-title">
					<h2>Quick links</h2>
					<a class="more" href="#">More links</a>
				</div>

				<?php the_field('quick_links', 'option'); ?>

			</div>
		</div>
	</div>
</div>
<div class="home-bg"></div>











<?php get_footer(); ?>
