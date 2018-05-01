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
		<!-- <a href="/wp-admin/post-new.php?category=woolworths"><img src="/wp-content/themes/hbaintranettheme/images/woolworths-logo.png" width="100" style="float:right"></a> -->
		<div style="clear:both"></div>
		<div class="home-title">
			<h2>News and insights</h2>
			<a class="more" href="<?php echo esc_url( home_url( '/' ) ); ?>category/all/">More news</a>
		</div>
		<!-- Begin posts -->
		<div class="home-news">
			
			<?php query_posts("showposts=20&cat=-26,-29,-36,-37&orderby=date&order=DESC"); ?> 
				
			<?php if (have_posts()) : while (have_posts()) : the_post(); ?>
				
				<?php get_template_part( 'template-parts/content', 'home' ); ?>

			<?php endwhile;?>

			<?php else : ?>

				<?php get_template_part( 'template-parts/content', 'none' ); ?>

			<?php endif; wp_reset_query(); ?>
		</div>
		<!-- End posts -->

		<!-- Begin posts
		<div class="home-meltwater">
			<h3>News from the industry</h3>
			<div class="melt-box">
				<iframe height="200" src="https://app.meltwater.com/gyda/outputs/5758b94f86f09efe024ad6d4/rendering?apiKey=550aa5e2bc48cc0f4efb6edd&type=html" frameborder="0" scrolling="no"></iframe>
			</div>
		</div>
		End posts -->
	</div>
	<div class="home-rhs">
		<div class="home-social" style="background:rgba(106,178,226,0.1);display:inline-block;width:100%;padding:20px;box-sizing:border-box;">
			<div class="social-stuff">
				<div class="home-title">
					<h2>Social stuff</h2>
				</div>
				<?php dynamic_sidebar( 'social-stuff' ); ?>
				<a class="cta" href="<?php echo esc_url( home_url( '/' ) ); ?>wp-admin/post-new.php?post_type=event">Share what’s happening</a>
			</div>
			<div class="promos">
				<div class="promo-container">
					<?php
					$args = array(
						'showposts'=> '6',
						'category_name'=> 'social'
					);
					query_posts($args); if (have_posts()) : while (have_posts()) : the_post(); 
					?>

						<?php get_template_part( 'template-parts/content', 'promos' ); ?>

					<?php endwhile;?>

					<?php else : ?>

						<?php get_template_part( 'template-parts/content', 'none' ); ?>

					<?php endif; wp_reset_query(); ?>
					<a href="#" class="prev">Prev</a> 
					<a href="#" class="next">Next</a>
				</div>
				<a class="cta cta-post" href="<?php echo esc_url( home_url( '/' ) ); ?>wp-admin/post-new.php?category_id=26">Post a picture</a>
			</div>
		</div>
		<div class="home-calendar">
			<div class="home-title">
				<h2>Calendar</h2>
				<a class="more" href="<?php echo esc_url( home_url( '/' ) ); ?>events/category/all/">See all events</a>
			</div>
			<?php dynamic_sidebar( 'out-and-about' ); ?>
			<?php dynamic_sidebar( 'upcoming-events' ); ?>
			<a class="cta" href="<?php echo esc_url( home_url( '/' ) ); ?>wp-admin/post-new.php?post_type=event">Add your event</a>
		</div>
		<div class="home-people-links">
			<div class="home-people">
				<div class="home-title">
					<h2>People</h2>
					<a class="more" href="<?php echo esc_url( home_url( '/' ) ); ?>people/">More people</a>
				</div>
				<div class="users-home">
					<?php
					
					$args = array(
						'blog_id'      => $GLOBALS['blog_id'],
						'role'         => '',
						'meta_key'     => '',
						'meta_value'   => '',
						'meta_compare' => '',
						'meta_query'   => array(),
						'date_query'   => array(),        
						'include'      => array(),
						'exclude'      => array(1),
						'orderby'      => 'login',
						'order'        => 'ASC',
						'offset'       => '',
						'search'       => '',
						'number'       => '',
						'count_total'  => false,
						'fields'       => 'all',
						'who'          => ''
					); 
					$blogusers = get_users( $args );
					// Array of WP_User objects.
					foreach ( $blogusers as $user ) {
						echo '<a class="person ' . esc_html( $user->home ) . '" href="' . site_url() . '/author/' . esc_html( $user->user_nicename ) . '">';
							echo get_avatar( $user->ID, 102 );
							echo '<div class="details">';
								echo '<h3>' . esc_html( $user->display_name ) . '</h3>';
								// echo '<br><span>' . esc_html( $user->user_url ) . '</span>';
								if (esc_html( $user->position )) {
									echo '<span class="detail"> ' . esc_html( $user->position ) . '</span>';
								}
								if (esc_html( $user->division )) {
									echo '<span class="detail"> ' . esc_html( $user->division ) . '</span>';
								}
								echo '<span class="detail detailPhs">';
									if (esc_html( $user->phone )) {
										echo '<span class="detailPh">T: ' . esc_html( $user->phone ) . '</span>';
									}
									if (esc_html( $user->mobile )) {
										echo '<span class="detailPh">M: ' . esc_html( $user->mobile ) . '</span>';
									}
								echo '</span>';
							echo '</div>';
						echo '</a>';
					}
					?>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="home-bg"></div>

<?php get_footer(); ?>
