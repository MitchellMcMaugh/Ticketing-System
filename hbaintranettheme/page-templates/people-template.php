<?php
/**
 * Template Name: People Template
 *
 * Description: Twenty Twelve loves the no-sidebar look as much as
 * you do. Use this page template to remove the sidebar from any page.
 *
 * Tip: to remove the sidebar from all posts and pages simply remove
 * any active widgets from the Main Sidebar area, and the sidebar will
 * disappear everywhere.
 *
 * @package WordPress
 * @subpackage Twenty_Twelve
 * @since Twenty Twelve 1.0
 */

get_header(); ?>

<div class="main-content">

	<div class="bread"><a href="<?php echo esc_url( home_url( '/' ) ); ?>">Home</a>&nbsp;&nbsp;/&nbsp;&nbsp;<?php the_field('page_title'); ?></div>

	<div class="lhs-menu">
		<?php if ( has_nav_menu( 'hba-way-menu' ) || has_nav_menu( 'social' ) ) : ?>
			<div class="menu">
				<?php if ( has_nav_menu( 'hba-way-menu' ) ) : ?>
					<?php
						wp_nav_menu( array(
							'theme_location' => 'hba-way-menu',
							'menu_class'     => 'hba-way-menu',
						 ) );
					?>
				<?php endif; ?>
			</div>
		<?php endif; ?>
	</div>

	<div class="center-content">

		<div class="content-head">
			<h2><?php the_field('page_title'); ?></h2>
		</div>

		<!-- Begin posts -->
		<div class="content-middle">
			<div class="ww">
			<?php while(has_sub_field("flexible_content")): ?>

				<?php if(get_row_layout() == "flexible_content"): // layout: Content ?>
			 
					<?php the_sub_field("content"); ?>
					
				<?php elseif(get_row_layout() == "subheading"): // layout: File ?>
			 
					<h3><?php the_sub_field('text'); ?></h3>

				<?php elseif(get_row_layout() == "hero_image"): // layout: File ?>

					<div class="ww-hero-image">
						<img src="<?php the_sub_field('image_hero'); ?>" />
					</div>

				<?php elseif(get_row_layout() == "embed"): // layout: File ?>

					<div class="ww-source-code"><?php the_sub_field('embed_code'); ?></div>
		
				<?php endif; ?>
			
			<?php endwhile; ?>

			<?php if(get_field('wwgallery')): ?>
				<div class="ww-gallery">
				<?php while(has_sub_field('wwgallery')): ?>

				<?php $image = wp_get_attachment_image_src(get_sub_field('photo'), 'medium'); ?>
				<a class="col swipebox" href="<?php echo $image[0]; ?>">
					<div class="photo" style="background-image: url(<?php echo $image[0]; ?>);" alt="<?php echo get_the_title(get_sub_field('photo')) ?>)"></div>
				</a>

				<?php endwhile; ?>
				</div>
			<?php endif; ?>
			</div>

			<div class="usersSearch">
				<input class="inputText" type="text" id="liveSearchBox" placeholder="Filter by name, position or division">
				<div class="filters">
					<span class="label">City:</span>
					<a href="#" class="all active">All</a>
					<a href="#" class="city">Perth</a>
					<a href="#" class="city">Sydney</a>
					<a href="#" class="city">Brisbane</a>
					<a href="#" class="city">Newcastle</a>
				</div>
			</div>

			<div class="users">
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
					echo '<div class="person" data-city="' . strtolower(esc_html( $user->city )) . '" data-value="' . esc_html( $user->user_nicename ) . strtolower(esc_html( $user->position )) . strtolower(esc_html( $user->division )) . '">';
						echo '<a href="' . site_url() . '/author/' . esc_html( $user->user_nicename ) . '">' . get_avatar( $user->ID, 102 ) . '</a>';
						echo '<div class="details">';
							echo '<h3><a href="' . site_url() . '/author/' . esc_html( $user->user_nicename ) . '">' . esc_html( $user->display_name ) . '</a></h3>';
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
					echo '</div>';
				}
				?>
			</div>
	
		</div>
		<!-- End posts -->

	</div>

	<div class="rhs-content">
		
	</div>


</div>



<?php get_footer(); ?>