<?php
/**
 * Template Name: Applications Template
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

	<div class="lhs-menu apps">
		<?php if ( has_nav_menu( 'applications-menu' ) || has_nav_menu( 'social' ) ) : ?>
			<div class="menu">
				<?php if ( has_nav_menu( 'applications-menu' ) ) : ?>
					<?php
						wp_nav_menu( array(
							'theme_location' => 'applications-menu',
							'menu_class'     => 'applications-menu',
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
		</div>
		<!-- End posts -->

	</div>

	<div class="rhs-content">
		
	</div>


</div>



<?php get_footer(); ?>